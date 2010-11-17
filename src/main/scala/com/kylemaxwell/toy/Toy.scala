package com.kylemaxwell.toy

import com.twitter.gizzard._
import org.eclipse.jetty.servlet._
import org.eclipse.jetty.server._
import ext._

object Toy {
	def main(args: Array[String]) {
	  if(args.length != 2) {
	    println("Usage: java c.k.t.Toy HOST PORT")
	    exit(1)
	  }
	  
    val host = args(0)
	  val port = args(1).toInt
	  
	  //Configuration
    val config = new SimpleLocalGizzardConfiguration("toy_test", "/tmp/toy")
    config.addShardType("com.kylemaxwell.toy.LuceneShard" -> new LuceneShardFactory(host))
    	  
	  // Instantiate and start the service
		val service = new ToyService(host, config).started()
	  
		
		// Bind it to a servlet/web service.
		val servlet = new ToyServlet(service)
		val server = new Server(port);
		val context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	  context.setContextPath("/");
	  server.setHandler(context);

	  context.addServlet(new ServletHolder(servlet),"/*");
	  server.start();
	  server.join();
	}
}