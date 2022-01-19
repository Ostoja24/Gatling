
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .inferHtmlResources(AllowList(), DenyList())
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
  
  private val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")
  
  private val headers_3 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  		"Accept-Encoding" -> "gzip, deflate, br",
  		"Accept-Language" -> "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Sec-GPC" -> "1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_4 = Map(
  		"Accept" -> "text/css,*/*;q=0.1",
  		"Accept-Encoding" -> "gzip, deflate, br",
  		"Accept-Language" -> "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7",
  		"Sec-Fetch-Dest" -> "style",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-GPC" -> "1"
  )


  private val scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get("/computers")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/assets/css/bootstrap.min.css"),
          http("request_2")
            .get("/assets/css/main.css")
        )
    )
    .pause(4)
    .exec(
      http("request_3")
        .get("/computers?f=acer")
        .headers(headers_3)
        .resources(
          http("request_4")
            .get("/assets/css/bootstrap.min.css")
            .headers(headers_4),
          http("request_5")
            .get("/assets/css/main.css")
            .headers(headers_4)
        )
    )
    .pause(1)
    .exec(
      http("request_6")
        .get("/computers/330")
        .headers(headers_3)
        .resources(
          http("request_7")
            .get("/assets/css/main.css")
            .headers(headers_4),
          http("request_8")
            .get("/assets/css/bootstrap.min.css")
            .headers(headers_4)
        )
    )
    .pause(2)
    .exec(
      http("request_9")
        .get("/computers")
        .headers(headers_3)
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
