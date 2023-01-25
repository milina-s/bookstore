package com.example.bookstore.config;

import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return bookRepository.findAll().isEmpty() ? args -> bookRepository.saveAll(List.of(
                new Book(1L, "Наприкінці приходить смерть", "./image/1.jpg", "Аґата Крісті", 166.0),
                new Book(2L, "Останній сеанс", "./image/2.jpg", "Аґата Крісті", 166.0),
                new Book(3L, "Вбивство у «Східному експресі»", "./image/3.jpg", "Аґата Крісті", 166.0),
                new Book(4L, "Смерть на Нілі", "./image/4.jpg", "Аґата Крісті", 120.0),
                new Book(5L, "І не лишилось жодного", "./image/5.jpg", "Аґата Крісті", 130.0),
                new Book(6L, "Володар мух", "./image/6.jpg", "Вільям Ґолдінґ", 179.0),
                new Book(7L, "Колекціонер", "./image/7.jpg", "Джон Фаулз", 199.0),
                new Book(8L, "1984", "./image/8.jpg", "Джордж Орвелл", 149.0),
                new Book(9L, "Колгосп тварин", "./image/9.jpg", "Джордж Орвелл", 99.0),
                new Book(10L, "Який чудесний світ новий!", "./image/10.jpg", "Олдос Гакслі", 219.0),
                new Book(11L, "На Західному фронті без змін", "./image/11.jpg", "Еріх Марія Ремарк", 206.0),
                new Book(12L, "Тріумфальна арка", "./image/12.jpg", "Еріх Марія Ремарк", 206.0),
                new Book(13L, "Різдво з червоним кардиналом", "./image/13.jpg", "Фенні Флеґґ", 211.0),
                new Book(14L, "Тисяча пам`ятних поцілунків", "./image/14.jpg", "Тіллі Коул", 238.0)
        )) : null;
    }
}
