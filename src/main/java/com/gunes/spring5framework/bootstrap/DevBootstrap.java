package com.gunes.spring5framework.bootstrap;

import com.gunes.spring5framework.model.Author;
import com.gunes.spring5framework.model.Book;
import com.gunes.spring5framework.model.Publisher;
import com.gunes.spring5framework.repository.AuthorRepository;
import com.gunes.spring5framework.repository.BookRepository;
import com.gunes.spring5framework.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public DevBootstrap(final AuthorRepository authorRepository,
                        final BookRepository bookRepository,
                        final PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Author author = new Author();
        author.setFirstName("Özgür");
        author.setLastName("IŞIK");
        Book book = new Book();
        book.setTitle("Driven Design");
        book.setISBN("123123123asd");
        author.setBooks(new HashSet<>());
        author.getBooks().add(book);
        Publisher publisher = new Publisher("İstanbul", "Özgür A.S");
        book.setPublisher(publisher);
        authorRepository.save(author);
        publisherRepository.save(publisher);
        bookRepository.save(book);

    }
}
