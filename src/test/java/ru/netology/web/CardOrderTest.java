package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTestSingleNameAndSurname() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванова Мария");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена!" +
                        " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestSingleNameDoubleSurname() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванова-Петрова Мария");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена!" +
                        " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestDoubleNameSingleSurname() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванова Мария-Виктория");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена!" +
                        " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestDoubleNameAndSurname() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванова-Петрова Мария-Виктория");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена!" +
                        " Наш менеджер свяжется с вами в ближайшее время."));
    }
}
