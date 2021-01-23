package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.type;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderInvalidTest {

    @Test
    void shouldTestFormIsEmpty() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNameInLatin() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Petrov Dima");
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNameInNumerals() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("535739792");
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestWithoutPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Петрова Виктория");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestPhoneWithoutPlus() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Петрова Виктория");
        form.$("[data-test-id=phone] input").setValue("89878888888");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678. "));
    }

    @Test
    void shouldTestPhoneWithRandomNumbers() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Петрова Виктория");
        form.$("[data-test-id=phone] input").setValue("543536");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678. "));
    }

    @Test
    void shouldTestPhoneWithLetters() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Петрова Виктория");
        form.$("[data-test-id=phone] input").setValue("frghyys");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678. "));
    }

    @Test
    void shouldTestPhoneWithoutCheckbox() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Петрова Виктория");
        form.$("[data-test-id=phone] input").setValue("+79878888888");
        form.$("button").click();
        $(".input_invalid").$("input").shouldHave(type("checkbox"));
    }
}
