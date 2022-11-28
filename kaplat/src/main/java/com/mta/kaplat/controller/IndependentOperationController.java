package com.mta.kaplat.controller;

import com.mta.kaplat.constants.Constants;
import com.mta.kaplat.logic.math.Calculator;
import com.mta.kaplat.logic.math.Operations;
import com.mta.kaplat.models.IndependentModel;
import com.mta.kaplat.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class IndependentOperationController {

    @RequestMapping("/independent/calculate")
    public ResponseEntity<ResponseModel> HelloWorld(@RequestBody IndependentModel data) {
        List<Integer> arguments = data.getArguments();
        String reqOperation = data.getOperation();
        Operations operation = Operations.valueOf(reqOperation.toUpperCase());

        int size = arguments.size();

        switch (operation) {
            case PLUS, MINUS, TIMES, POW, DIVIDE -> {
                if (size > 2) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseModel(-1, Constants.ERROR_TOO_MANY_ARGUMENTS + reqOperation));
                } else if (size < 2) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseModel(-1, Constants.ERROR_NO_ARGUMENTS + reqOperation));
                }
            }
            case FACT, ABS -> {
                if (size > 1) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseModel(-1, Constants.ERROR_TOO_MANY_ARGUMENTS + reqOperation));
                } else if (size < 1) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseModel(-1, Constants.ERROR_NO_ARGUMENTS + reqOperation));
                }
            }
            default -> {
                // ERROR NO OPERATION
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ResponseModel(-1, Constants.ERROR_NO_OPERATION + reqOperation));
            }
        }

        if(operation == Operations.DIVIDE && arguments.get(1) == 0)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel(-1, Constants.ERROR_DIVIDE_ZERO));
        else if(operation == Operations.FACT && arguments.get(0) < 0)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel(-1, Constants.ERROR_FACT_NEGATIVE));

        return ResponseEntity.ok(new ResponseModel(Calculator.makeIndependentOperation(operation, arguments), ""));
    }
}