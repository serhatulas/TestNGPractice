import Utilities.Driver;
import Utilities.JavaValueReader;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.time.Duration;

public class Example {

          public Example() {
             PageFactory.initElements(Utilities.Driver.get(), this);
          }

         @Test
         public void test1_DragandDrop() throws InterruptedException {

            Driver.get().get(JavaValueReader.get("url","configuration"));
            WebElement AButton= Driver.get().findElement(By.xpath("//*[text()='A']"));
            WebElement BButton= Driver.get().findElement(By.xpath("//*[text()='B']"));
            JavascriptExecutor js = (JavascriptExecutor)Driver.get();
             js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                     + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                     + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                     + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                     + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                     + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                     + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                     + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                     + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                     + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                     + "var dropEvent = createEvent('drop');\n"
                     + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                     + "var dragEndEvent = createEvent('dragend');\n"
                     + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                     + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                     + "simulateHTML5DragAndDrop(source,destination);", AButton, BButton);
         }

    }

