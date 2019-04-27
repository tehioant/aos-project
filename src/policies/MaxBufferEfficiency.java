/**
 * 
 Harmonia  -  Maximum  Buffer  System  Efficiency:
 
In this  policy,  Harmonia  aims  to  optimize  the  buffering  system 
efficiency by maximizing the buffer utilization (i.e. minimizing idleness)  while  at  the  same  time  maximizing  the  average
available  capacity  of  the  disks.  To  maintain  high  available
remaining  capacity,  Harmonia  hides  flushing  behind  computation phases of the same or another running application, and
thus,  keeps  the  buffers  busy  at  all  times.  This  policy  is  ideal
when  most  of  the  apps  share  similar  characteristics  and  no
one  app  requires  special  treatment.  The  cost  function  in  the
DP algorithm (i.e., Cij from eq. 3) is defined as:
Cost(i, j)Buffer−Efficiency = BU + CR / 2

where BU is the average buffer utilization and is calculated
as the ratio of the time when buffers are serving an app over the
completion time of the last app, CR
is the average remaining capacity of the buffers. Harmonia considers if scheduling the
ith I/O phase on the jth buffer will increase the buffer 
utilization while maintaining the same or smaller average remaining
capacity  leading  to  better  buffer  efficiency.  In  our  example,
Harmonia,  under  this  policy  will  schedule  app  1  at t=0  and
will run on top of both buffers hence maximum I/O bandwidth.
Lib 2 will start at t=2, effectively overlapping its computation
phases with the I/O phase of app 1. Additionally, the buffers
will start flushing as soon as data are available, practically at
t=3,  and  it  will  keep  flushing  during  both  computations  and
I/O phases. The rest of the apps will be scheduled once one of
the previous apps finishes. This ensures the constraints of our
buffering system are intact. The number of concurrent accesses
on one  buffer are  kept low  (one I/O  phase and  flushing) and
thus, the interference factor remains low.


**/
package policies;

import java.util.ArrayList;
import java.util.LinkedList;

import dispatcher.DispatcherInterface;
import dispatcher.model.Buffer;
import requests.Request;
import solver.ProcessSolver;

public class MaxBufferEfficiency extends Policy{

	
	
	
	public MaxBufferEfficiency(DispatcherInterface dispInterface, LinkedList<Request> queue){
		super(dispInterface, queue);
	}
	
	public MaxBufferEfficiency() {
		this(null, null);
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MaxBufferEfficiency";
	}

	
	
	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {
		ArrayList<ProcessSolver> scheduled = new ArrayList<ProcessSolver>();
		ArrayList<Buffer> buffers;
		int r = 0;
		long diff = 999999999;
		int sizeBuffer = super.getDispInterface().getBuffers().size();
		long ressources;
		for(Request request : queue){
			buffers = super.getDispInterface().getBuffers();
			for(int index=0; index < sizeBuffer; index++){
				ressources = buffers.get(index).getCurrentRessources();
				if(ressources > request.getPayload()){
					if( ressources - request.getPayload() < diff){
						diff = ressources - request.getPayload();
						r = index;
					}
				}
			}
			scheduled.add(new ProcessSolver(request, r));
		}
		return scheduled;
	}


	@Override
	public long costFunction(ArrayList<ProcessSolver> scheduled) {
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	

}
