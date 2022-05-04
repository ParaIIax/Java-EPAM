package ua.advanced.task6.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GitRepoObservers {
  //no changes were made to other classes
    public static Repository newRepository(){
        return new Repository() {
            List<Event> events = new ArrayList<>();
            List<WebHook> webHooks = new ArrayList<>();

            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                List<Commit> commits = new ArrayList<>();
                commits.add(commit);
                Event event = new Event(Event.Type.COMMIT, branch, commits);
                for(WebHook cur_webHook : webHooks){
                    if(cur_webHook.type() == Event.Type.COMMIT && cur_webHook.branch() == branch) {
                        cur_webHook.onEvent(event);
                        events.add(event);
                    }
                }
                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                List<Event> events2 = new ArrayList<>();
                for(var cur_event: events) {
                    if (cur_event.branch().equals(sourceBranch))
                        events2.add(cur_event);
                }
                List<Commit> commits = new ArrayList<>();
                for(var cur_event : events2) {
                    List<Commit> commits2 = cur_event.commits();
                    commits.add(commits2.get(0));
                }
                Event event = new Event(Event.Type.MERGE, targetBranch, commits);
                for(WebHook cur_webHook : webHooks) {
                    if(cur_webHook.type() == Event.Type.MERGE && Objects.equals(cur_webHook.branch(), event.branch())) {
                        cur_webHook.onEvent(event);
                        events2.add(event);
                    }
                }
            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHook() {
            List<Event> events = new ArrayList<>();
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                return events;
            }

            @Override
            public void onEvent(Event event) {
                events.clear();
                events.add(event);
            }
        };
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHook() {
            List<Event> events = new ArrayList<>();
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {
                return events;
            }

            @Override
            public void onEvent(Event event) {
                events.add(event);
            }
        };
    }

}
