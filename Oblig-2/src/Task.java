class Task{
    int id, time, staff;
    String name;
    int earliestStart, latestStart;
    int[] outEdges;
    int cntPredecessors;
    int cntVisit;//teller hvor mange ganger er besokt
    boolean visited;
    int[] dependencies;
    int slackcnt;//den er for slack

    public Task(int id, String name, int time, int staff, int[] dependencies){
        this.id = id;
        this.name = name;
        this.time = time;
        this.staff = staff;
        cntPredecessors = dependencies.length;
        this.cntVisit = 0;
        visited = false;
        this.dependencies = dependencies;
        slackcnt = 0;
    }//gjor adtask på taskgenerator

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public int getStaff() {
        return staff;
    }

    public String getName() {
        return name;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public int getLatestStart() {
        return latestStart;
    }

    public int getCntVisit() {
        return cntVisit;
    }

    public int getCntPredecessors() {
        return cntPredecessors;
    }

    public boolean isVisited() {
        return visited;
    }

    public int[] getOutEdges() {
        return outEdges;
    }

    public int[] getDependencies() {
        return dependencies;
    }

    public void printEdges(){
        if (outEdges == null){
            System.out.println();
            return;
        }
        for (int i = 0; i<outEdges.length; i++){
            System.out.print(outEdges[i] + " ");
        }
        System.out.println();
    }
}