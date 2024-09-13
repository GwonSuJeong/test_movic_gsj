package com.icia.movic.dao;

import com.icia.movic.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {

    void insertPayment(PaymentDto payment);
}
