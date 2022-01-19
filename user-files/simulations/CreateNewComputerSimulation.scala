
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class CreateNewComputerSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""))
    .acceptHeader("text/css,*/*;q=0.1")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
  
  private val headers_0 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  		"Cache-Control" -> "max-age=0",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Sec-GPC" -> "1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_1 = Map(
  		"Sec-Fetch-Dest" -> "style",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-GPC" -> "1"
  )
  
  private val headers_3 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Sec-GPC" -> "1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_6 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  		"Cache-Control" -> "max-age=0",
  		"Origin" -> "https://computer-database.gatling.io",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Sec-GPC" -> "1",
  		"Upgrade-Insecure-Requests" -> "1"
  )


  private val scn = scenario("CreateNewComputerSimulation")
    .exec(
      http("request_0")
        .get("/computers")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/assets/css/main.css")
            .headers(headers_1),
          http("request_2")
            .get("/assets/css/bootstrap.min.css")
            .headers(headers_1)
        )
    )
    .pause(2)
    .exec(
      http("request_3")
        .get("/computers/new")
        .headers(headers_3)
        .resources(
          http("request_4")
            .get("/assets/css/main.css")
            .headers(headers_1),
          http("request_5")
            .get("/assets/css/bootstrap.min.css")
            .headers(headers_1)
        )
    )
    .pause(30)
    .exec(
      http("request_6")
        .post("/computers")
        .headers(headers_6)
        .formParam("name", "Huawei")
        .formParam("introduced", "2021-12-20")
        .formParam("discontinued", "2021-12-21")
        .formParam("company", "2")
    )

	setUp(scn.inject(atOnceUsers(100000))).protocols(httpProtocol)
}
