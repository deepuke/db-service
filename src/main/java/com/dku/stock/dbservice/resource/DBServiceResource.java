package com.dku.stock.dbservice.resource;

import com.dku.stock.dbservice.model.Quote;
import com.dku.stock.dbservice.model.Quotes;
import com.dku.stock.dbservice.repository.QuoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {
    private QuoteRepository quoteRepository;

    public DBServiceResource(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {
        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quoteRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {
        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quoteRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }
}
