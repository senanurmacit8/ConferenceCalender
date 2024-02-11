package com.conference.calender;

import com.conference.calender.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.util.List;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class TRest{

        @Autowired
        private UserService bookService;

        @Test
        public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
            //List<Book> books = bookService.list();

            //Assert.assertEquals(books.size(), 3);
        }

}
