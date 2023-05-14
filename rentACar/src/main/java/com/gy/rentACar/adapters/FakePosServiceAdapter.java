package com.gy.rentACar.adapters;

import com.gy.rentACar.business.abstracts.PosService;
import com.gy.rentACar.common.constants.Messages;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {

    @Override
    public void pay() {
        boolean isPaymentSuccessful=new Random().nextBoolean();
        if (!isPaymentSuccessful)
            throw new RuntimeException(Messages.Payment.Failed);
    }
}
