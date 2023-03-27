package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class OrderDeliveryCardTest {
    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldTestValid() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").setValue("Ефанов-Иванов Василий");
        $("[data-test-id='phone'] input").setValue("+78568453221");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").should(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestValideNew() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ка");
        $$("div.popup__content div").find(exactText("Калининград")).click();
        String planningDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("span[data-test-id='date'] button").click();
        $$("div.calendar__arrow.calendar__arrow_direction_right").get(1).click();
        $$("table.calendar__layout td").get(7).click();
        $("[data-test-id='name'] input").setValue("Ефанов-Иванов Василий");
        $("[data-test-id='phone'] input").setValue("+78568453221");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").should(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }


}
