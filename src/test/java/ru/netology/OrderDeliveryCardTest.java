package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class OrderDeliveryCardTest {

    @Test
    void shouldTestValid() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue("29.03.2023");
        $("[data-test-id='name'] input").setValue("Ефанов-Иванов Василий");
        $("[data-test-id='phone'] input").setValue("+78568453221");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestValideNew() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ка");
        $$("div.popup__content div").find(exactText("Калининград")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("span[data-test-id='date'] button").click();
        $$("table.calendar__layout td").find(text("30")).click();
        $("[data-test-id='name'] input").setValue("Ефанов-Иванов Василий");
        $("[data-test-id='phone'] input").setValue("+78568453221");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").should(Condition.appear, Duration.ofSeconds(15));
    }


}
