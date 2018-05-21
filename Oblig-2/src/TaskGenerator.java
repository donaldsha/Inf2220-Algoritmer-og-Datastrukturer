import java.util.HashMap;
import java.util.LinkedList;

class TaskGenerator{

    Task edges;
    HashMap<Integer, Task> tasks;

    TaskGenerator(){
        tasks = new HashMap<Integer, Task>();
    }

    public void adTask(int id, String name, int time, int staff, int[] dependencies){
        Task newTasks = new Task(id, name, time, staff, dependencies);
        tasks.put(id, newTasks);
    }

   public void update(){//oppdater variablene for nodene i graph
        for (Task t: tasks.values()) {
            for (int i = 0; i <t.dependencies.length ; i++) {//fyller opp arrayen outEdges etter hvert task 
                Task getUpdated = tasks.get(t.dependencies[i]);
                
                if (getUpdated == null){
                    continue;
                }else if (getUpdated.outEdges == null){
                    getUpdated.outEdges = new int[1];
                    getUpdated.outEdges[0] = t.id;
                }else{
                    int[] arr = new int[getUpdated.outEdges.length + 1];

                    for (int j = 0; j < getUpdated.outEdges.length; j++) {
                        arr[j] = getUpdated.outEdges[j];
                    }
                    arr[getUpdated.outEdges.length] = t.id;
                    getUpdated.outEdges = arr;
                    //System.out.println(arr);
                }
            }
        }
    }

    public LinkedList<Task> loopTasks(){
        for (Task t: tasks.values()){
            LinkedList<Task> taskCnt = loop(t, new LinkedList<Task>());
            if (taskCnt != null){
                return taskCnt;
            }
        }
        return null;
    }

     public LinkedList<Task> loop(Task t, LinkedList<Task> list){//Dybde-først søk, metode jeg bruker som hjelpe metode fr loopTasks
        if (list.contains(t)){
            //starter nodene forst slik at vi har bare loopen
            //fosrte og siste er samme node
            while (list.peek() != t) list.poll();
            list.add(t);
            return list;
        }else{
            list.add(t);
            if (t.outEdges != null){
                for (int i = 0; i<t.outEdges.length; i++){
                    list = loop(tasks.get(t.outEdges[i]), list);
                    if (list != null){
                        return list;
                    }
                }
            }else {
                return null;//hvis ingen naboer, edges
            }
        }
        return null;
    }

    public LinkedList<Task> sort(){
        LinkedList<Task> list = new LinkedList<Task>();
        boolean loops = false;
        while (list.size() < tasks.size()){
            loops = true;
            //System.out.println("list.size() " + list.size());
            //System.out.println("tasks.size() " + tasks.size());
            for (Task t: tasks.values()) {
                if (t.cntPredecessors - t.cntVisit == 0 && !t.visited){
                    t.visited = true;
                    loops = false;
                    list.add(t);

                    for (int i = 0; t.outEdges != null && i< t.outEdges.length; i++) {
                        Task counted = tasks.get(t.outEdges[i]);
                        //System.out.println("\tIncrementing cntVisit for " + counted.id);
                        counted.cntVisit++;
                        //System.out.println("\tIt is now " + counted.cntVisit);
                    }
                }
            }
            //System.out.pr;
            if (loops == true){//Her hvis vi finnes ikke noen task så betyr det at vi har en loop
                System.out.println("Running in loop");
                return null;
            }//Her hadde jeg problemmet forrige gang men naa fikset jeg den og alt fungerer :D
        }

        for (Task t : tasks.values()) {
            t.cntVisit = 0;
            t.visited = false;
        }
        return list;
    }

    public void printSort(){//her skriver jeg ut alle taskene 
        int startTime = -1;
        int runiTasks = 0;
        int currStaff = 0;

        LinkedList<Task> queue = new LinkedList<Task>();

        while (queue.size() < tasks.size() || runiTasks > 0){
            startTime++;
            String printout = "Time: " + startTime + "\n";
            boolean print = false;

            for (Task t: tasks.values()) {
                if (queue.contains(t) && t.cntPredecessors - t.cntVisit== 0 && !t.visited && (startTime == t.earliestStart + t.time)){
                    printout += "\tfinished: " +t.id + "\n";
                    print = true;
                    t.visited = true;
                    runiTasks--;
                    currStaff -= t.staff;
                    for (int i = 0;t.outEdges != null && i < t.outEdges.length; i++) {
                        Task edge = tasks.get(t.outEdges[i]);
                        edge.cntVisit++;
                    }
                }
            }
            //Se om vi kan starte en ny task
            for (Task t: tasks.values()) {
                if (t.cntPredecessors - t.cntVisit == 0 && !t.visited && !queue.contains(t)){
                    queue.add(t);
                    printout += "\tStarting: " + t.id + "\n";
                    print = true;
                    t.earliestStart = startTime;
                    runiTasks++;
                    currStaff += t.staff;
                }
            }
            for (Task t: tasks.values()) {
                if (t.visited == true && t.outEdges != null) {
                    boolean started = false;
                    for (int i = 0; i < t.outEdges.length; i++) {
                        if (queue.contains(tasks.get(t.outEdges[i]))) {
                            started = true;
                        }
                    }
                    if (!started) {
                        t.slackcnt++;
                    }
                } else if (t.visited == true && t.outEdges == null) {
                        if (runiTasks > 0) {
                            t.slackcnt++;
                        }
                }
            }
            if (currStaff > 0){
                printout += "\tCurrent Staff: " + currStaff + "\n";
            }
            if (print){
                System.out.println(printout);
            }
        }
        System.out.println("**** Shortest possible project execution is" + startTime + " ****");
        System.out.println(" ID  \tName   \t\t\t\t  Time Needed\t\tManPower  \tEarliest Srtart Latest Start  \tSlack\tEdges");
         for (Task t: tasks.values()){
             t.latestStart = t.earliestStart + t.slackcnt;
             System.out.format("%3d\t%-40s%3d%15d%17d%16d %15d\t", t.getId(), t.getName(), t.getTime(), t.getStaff(), t.getEarliestStart(), t.getLatestStart(), t.slackcnt);
             t.printEdges();
         }
    }
}

