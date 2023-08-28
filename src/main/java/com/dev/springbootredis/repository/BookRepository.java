package com.dev.springbootredis.repository;

import com.dev.springbootredis.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BookRepository {

    private static final String HASH_KEY = "Book";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Book save(Book book){
        redisTemplate.opsForHash().put(HASH_KEY,book.getId(),book);
        return book;
    }

    public List<Book> findAll(){
        return Collections.singletonList((Book) redisTemplate.opsForHash().values(HASH_KEY));
    }

    public Book findBookById(int id){
        return (Book) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteBookById(int id){
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Deleted successfully";
    }


}
