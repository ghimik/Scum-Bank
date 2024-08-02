package com.example.demo.services;

import com.example.demo.models.projections.TransactionProjection;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDataControllingService {

    String getUsername(Long id);

    BigDecimal getUserBalance(Long id);

    List<TransactionProjection> getTransactionsList(Long id);

    byte[] getAvatar(Long id);

    void setAvatar(MultipartFile blob, Long id) throws IOException;
}
