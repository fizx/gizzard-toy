package com.kylemaxwell.toy

import javax.servlet.http._
import com.twitter.json.Json
import org.apache.commons.io.IOUtils

class ToyServlet(service: ToyService) extends HttpServlet {
	override def doGet(req: HttpServletRequest, rsp: HttpServletResponse) = {
		val query = req.getParameter("q")
		val local = req.getParameter("local") != null
		
		query match {
			case null => respond(rsp, Map("status" -> "error", "message" -> "no query given"))
			case _ => respond(rsp, Map("status" -> "ok", "results" -> service.query(query, local)))
		}
	}
	
	override def doPost(req: HttpServletRequest, rsp: HttpServletResponse) = {
		service.put(req.getPathInfo, IOUtils.toString(req.getReader))
    respond(rsp, Map("status" -> "ok"))
	}
	
	override def doDelete(req: HttpServletRequest, rsp: HttpServletResponse) = {
		service.delete(req.getPathInfo)
		respond(rsp, Map("status" -> "ok"))
	}
	
	private def respond(rsp: HttpServletResponse, map: Map[String, Any]) {
	  rsp.getWriter.println(Json.build(map))
	}
}
