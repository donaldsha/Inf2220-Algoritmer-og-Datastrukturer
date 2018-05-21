import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
class FileReader{
	TaskGenerator tasks;

	public void readFile(String file, TaskGenerator tasks){
	    this.tasks = tasks;

	    Scanner sc = null;
	    try{
	        sc = new Scanner(new File(file));
        }catch (IOException e){
	        e.printStackTrace();
        }

        int taskCnt = Integer.parseInt(sc.nextLine());
        while(sc.hasNext()){//en task for hver succeding linje
            String line = sc.nextLine().trim();
            if (line.isEmpty()){
                continue;
            }
            String[] lines = line.split("\\s+");
            int id = Integer.parseInt(lines[0].trim());//task id

            String name = lines[1].trim();//task name
            int time = Integer.parseInt(lines[2].trim());//estimated time for the task
            int staff = Integer.parseInt(lines[3].trim());//staff required to finish the task

            int[] edges = new int[lines.length-5];
            int x = 4;
            while (x < lines.length-1){
                edges[x-4] = Integer.parseInt(lines[x].trim());
                x++;
            }
            tasks.adTask(id, name, time, staff, edges);//add to task
        }
        sc.close();
    }

    public void update(){
	    if (tasks == null){
	        System.out.print("Empty Graph");
        }
        tasks.update();
    }

    public void sort(){
        LinkedList<Task> t = tasks.sort();
        //System.out.println(t);
        if (t != null){//Vi vet vi får en loop når t==null
            tasks.printSort();
        }else{
           loopTasks();//Hvis vi finner en loop
        }
    }

    public void loopTasks(){
        LinkedList<Task> t = tasks.loopTasks();
        if (t != null){
            while (t.size() > 0)System.out.print(t.poll().id+"->");
             System.out.println("");
        }
    }
}