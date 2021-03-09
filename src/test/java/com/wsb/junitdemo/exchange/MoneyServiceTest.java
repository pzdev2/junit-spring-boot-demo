package com.wsb.junitdemo.exchange;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class MoneyServiceTest {

    @Mock
    CurrencyService currencyService;

    @InjectMocks
    MoneyService moneyService;

    @Test
    void returns_amount_in_pln() {
        lenient().when(currencyService.getRate("USD")).thenReturn(1.5f);
        lenient().when(currencyService.getRate("EUR")).thenReturn(3f);

        assertAll("exchanges",
                () -> assertEquals(3f, moneyService.getInPLN(2f, "USD")),
                () -> assertEquals(15f, moneyService.getInPLN(5f, "EUR"))
        );
    }
}