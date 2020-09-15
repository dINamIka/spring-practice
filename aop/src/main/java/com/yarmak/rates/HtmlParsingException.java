package com.yarmak.rates;

public class HtmlParsingException extends Throwable {

    public HtmlParsingException(final String msg) {
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
