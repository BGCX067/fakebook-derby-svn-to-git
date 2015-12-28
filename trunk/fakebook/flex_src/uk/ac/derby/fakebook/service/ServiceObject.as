package uk.ac.derby.fakebook.service
{
	import flash.sampler.startSampling;
	
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class ServiceObject extends RemoteObject
	{
		private static const _instance:ServiceObject = new ServiceObject(ServiceLock);
		
		
		public function ServiceObject(lock:Object)
		{
			super("fakebookService");
			if (lock != ServiceLock){
				throw new Error ("There should be only one Service Object");
			}
		}
		
		public static function get instance():ServiceObject{
			return _instance;
		}
		
		
		public function doLogIn(username:String,password:String,callback:Function,err:Function):void{
			var op:AbstractOperation;
			op= this.getOperation("doLogin");
			op.addEventListener(ResultEvent.RESULT,callback);
			op.addEventListener(FaultEvent.FAULT,err);
			op.send(username,password);
			
		}
		
		
		
	}
}

class ServiceLock {}