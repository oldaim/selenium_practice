import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration

fun main(args: Array<String>) {

    val chromeOption = ChromeOptions()

    System.setProperty("webdriver.chrome.driver","/Users/kimdonggyun/Desktop/personal/selenium_practice/chromedriver")

    val webDriver = ChromeDriver(chromeOption)


    process(webDriver)


}

fun process(webDriver: ChromeDriver){

    val url = "https://www.fmkorea.com/hotdeal"
    val findString = "나이키"

    webDriver.get(url)

    webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000))

    val list = webDriver
        .findElements(By.tagName("li"))
        .filter {
            it
                .getAttribute("class")
                .equals("li  li_best2_pop0 li_best2_hotdeal0")
    }.toList()

    val map = HashMap<String,String>()

    list.forEach { el ->

        val title = el.findElement(By.tagName("h3")).text
        val link = el.findElement(By.tagName("a")).getAttribute("href")
        val hotDealEnd = el.findElement(By.tagName("h3")).findElement(By.tagName("a")).getAttribute("class")

        if(hotDealEnd !== "hotdeal_var8Y" && title.contains(findString))
        {
            map[title] = link
        }else{
            println(title)
        }

    }

    for (el in map){
        println(el.toString())
    }

    webDriver.quit()


}