
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class DatabaseSimulation3 extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""))
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
  
  private val headers_1 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  		"Accept-Encoding" -> "gzip, deflate, br",
  		"Accept-Language" -> "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Sec-GPC" -> "1"
  )


  private val scn = scenario("DatabaseSimulation3")
    //OPEN
    .exec(
      http("request_0")
        .get("/computers")
    )
    //SEARCH acer
    .pause(4)
    .exec(
      http("request_1")
        .get("/computers?f=acer")
        .headers(headers_1)
    )
    //CHOOSE A RECORD
    .pause(2)
    .exec(
      http("request_2")
        .get("/computers/330")
        .headers(headers_1)
    )
    //HOME PAGE
    .pause(3)
    .exec(
      http("request_3")
        .get("/computers")
        .headers(headers_1)
    )

	setUp(scn.inject(atOnceUsers(2000))).protocols(httpProtocol)
}
