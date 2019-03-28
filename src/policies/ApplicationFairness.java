/**

Harmonia  -  Application  Fairness:

In this policy, Harmonia  aims  to  offer  absolute  fairness  in  terms  of  waiting
and  execution  time  to  all  scheduled  applications.  Resources
are  divided  equally  into  all  applications.  Harmonia  ensures
that  it  does  not  violate  the  constraints  though.  In  our  study
in  Section  III-B  we  found  that  more  than  three  applications
on  one  buffer  node  is  really  hurtful  in  terms  of  performance
(i.e.,If greater  than  5x).  This  policy  takes  into  account  this
fact  when  dividing  the  resources.  If  the  total  number  of  I/O
phases  to  be  scheduled  cannot  fit  into  the  available  buffers,
Harmonia  randomly  selects  which  ones  to  schedule  at  this
point  in  time  and  which  ones  later.  The  randomness  ensures
that no application will be favored over time. The cost function
in the DP algorithm is defined as:
Cost(i, j)Fairness = C_timeLastApp − C_timeFirstApp

where C_time is the completion time of the application. Completion  time  encapsulates  both  the  wait  time  to  be  scheduled
and  the  bandwidth  available  to  each  application  since  it  is
a  function  of  both.  This  cost  represents  the  time  distance
between  the  first  and  the  last  application  to  finish.  Typically,
a  totally  fair  system  will  have  this  distance  equal  to  zero.
All  applications  are  expected  to  have  the  same  completion
time. This policy is effective in homogeneous systems and also
to  applications  that  can  sacrifice  high  performance  in  favor
of  earlier  access  to  the  buffers.  In  our  example,  Harmonia
first  checks  if  the  6  applications  violate  the  constraint  of  up
to  3  apps  to  1  buffer.  Coincidentally  in  this  case,  Harmonia
can  indeed  start  all  6  applications  at  the  same  time
t=0  by scheduling  apps  1-3  on  the  first  buffer  and  apps  4-6  on  the
second buffer node. Another constraint is that the buffers can
fit up to 4 I/O phases before they run out of space. Harmonia
will  choose  to  tolerate  added  interference  and  will  start  a
background  flushing  (notice  that  between t=7  and t=9  the
I/O  overlaps  with  flushing  which  continues  during  the  next
computation).  The  decisions  made  by  Harmonia  result  in  a
situation  where  all  6  applications  are  allowed  to  continue
execution  even  though  they  get  less  bandwidth  and  thus,
extended I/O phases due to interference.


**/

package policies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dispatcher.DispatcherInterface;
import dispatcher.model.Application;
import dispatcher.model.Buffer;
import dispatcher.model.ProcessBuffer;
import requests.Request;
import solver.ProcessSolver;

public class ApplicationFairness extends Policy{
	
	
	Random random = new Random();
	ArrayList<Application> appList;
	
	
	public ApplicationFairness(DispatcherInterface dispInterface, LinkedList<Request> request){
		super(dispInterface, request);
		this.appList = this.getAllApps(request);
	}
	
	public ApplicationFairness() {
		this(null, null);
	}

	@Override
	public String getPolicyName() {
		return "ApplicationFairness";
	}

	

	
	// All  applications  are  expected  to  have  the  same  completion time
	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {
		ArrayList<ProcessSolver> scheduled = new ArrayList<ProcessSolver>();
		int ran = this.appList.size();
		int randomReq;
		int bufferId;
		while(true){
			if(this.appList.size()/3 < super.getDispInterface().getNbMaxBuffers()){ // not enough buffers for every app
				for(Application app : this.appList){ // the scheduling is made randomly between apps
					randomReq = random.nextInt(ran-1);
					// TODO   Must ensure that all App have the same ressources
					for(Buffer buffer : super.getDispInterface().getBuffers()){
						if(buffer.getProcess().size() < 3 && buffer.getCurrentRessources() > this.appList.get(randomReq).getAppPayload()){
							for(Request request : this.appList.get(randomReq).getListRequest()){
									scheduled.add(new ProcessSolver(request, buffer.getId()));
							}
							break;
						}
					}
					ran --;
				}
				break;
			} else {
				// TODO Else when more than 3 app by buffer
			}
		}
		return scheduled;
	}
	
	
	
	
	public ArrayList<Application> getAllApps(LinkedList<Request> queue){
		ArrayList<Application> apps = new ArrayList<Application>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(queue.get(0).getAppId());
		Application application;
		for(Request item : queue ){
			for(int i : list){
				if(item.getAppId() != i){
					list.add(item.getAppId());
				}
			}
		}
		for(int app : list){
			application = new Application();
			application.setAppId(app);
			apps.add(application);
		}
		for(Request request : queue){
			for(Application m : apps){
				if(request.getAppId() == m.getAppId()){
					m.addRequest(request);
				}
			}
		}
		return apps;
	}
	
	public Application getApplication(LinkedList<Request> queue, int id){
		Application app = new Application();
		app.setAppId(id);
		for(Request request : queue){
			if(request.getAppId() == id)
				app.addRequest(request);
		}
		app.getAppPayload();
		app.getAppCompTime();
		return app;
	}
	
	
	
	
	
	
	public int costFunction(ArrayList<Integer> schedule, LinkedList<Request> queue){
		return 0;
	}

	
	
	
	@Override
	public long costFunction(ArrayList<ProcessSolver> scheduled) {
		Application firstApp = null;
		long cTimeFirst = 0;
		long cTimeLast = 0;
		Application lastApp = null;
		for(Application app : this.appList){
			if(app.getAppId() == scheduled.get(0).getRequest().getAppId())
				firstApp = app;
			if(app.getAppId() == scheduled.get(scheduled.size()-1).getRequest().getAppId())
				lastApp = app;
		}
		for(Buffer buffer : super.getDispInterface().getBuffers()){
			for(ProcessBuffer proc : buffer.getProcess()){
				cTimeFirst += proc.getRequest().getCompletionTime();
				if(firstApp.getAppId() == proc.getRequest().getAppId()){
					break;
				}
			}
			if(cTimeFirst > 0){
				break;
			}
		}
		for(Buffer buffer : super.getDispInterface().getBuffers()){
			for(ProcessBuffer proc : buffer.getProcess()){
				cTimeLast += proc.getRequest().getCompletionTime();
				if(lastApp.getAppId() == proc.getRequest().getAppId()){
					break;
				}
			}
			if(cTimeLast > 0){
				break;
			}
		}
		return cTimeFirst - cTimeLast;
	}

	
	
	
	
	

}
