package com.hadly.validation;

import com.hadly.validation.annotation.QueryConstraint;

import java.util.Date;

/**
 * Created by hadly on 2017/6/21.
 */
@QueryConstraint
public class OrderQuery implements IUniValidator{
    private Date from;
    private Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
