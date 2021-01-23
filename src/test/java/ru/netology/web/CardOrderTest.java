package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldTestSingleNameAndSurname() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванова Мария");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestSingleNameDoubleSurname() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванова-Петрова Мария");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestDoubleNameSingleSurname() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванова Мария-Виктория");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestDoubleNameAndSurname() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванова-Петрова Мария-Виктория");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
