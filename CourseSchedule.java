class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees=new int[numCourses];
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        for(int[] pr:prerequisites){
            indegrees[pr[0]]++;
            
            
            if(!map.containsKey(pr[1])){
                map.put(pr[1],new ArrayList<>());
            }
            map.get(pr[1]).add(pr[0]);
        }
        int count=0;
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegrees[i]==0){
                q.add(i);
                count++;
            }
        }
        if(count==numCourses) return true;
        if(q.isEmpty()) return false;
        
        
        while(!q.isEmpty()){
            int curr=q.poll();
            List<Integer> dependents =map.get(curr);
            if(dependents!=null){
                for(int dependent:dependents){
                    indegrees[dependent]--;
                    if(indegrees[dependent]==0){
                        q.add(dependent);
                        count++;
                        if(count==numCourses) return true;
                    }
                }
            }
        }
        return false;
    }
}