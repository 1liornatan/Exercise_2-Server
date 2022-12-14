package com.mta.kaplat.logic.math;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class NumbersStack {
    final Stack<Integer> numbersStack;

    public NumbersStack() {
        numbersStack = new Stack<>();
    }

    public void delete(Integer count) {
        for(int i = 0; i < count; i++)
            numbersStack.pop();
    }

    public List<Integer> popToList(int count) {
        List<Integer> arrayList = new ArrayList<>();

        for(int i = 0; i < count; i++)
            arrayList.add(numbersStack.pop());

        return arrayList;
    }

    public Integer size() {
        return numbersStack.size();
    }

    public void push(int num) {
        numbersStack.push(num);
    }

    @Override
    public String toString() {
        List<Integer> numbersList = numbersStack.stream().collect(Collectors.toList());
        Collections.reverse(numbersList);

        return numbersList.toString();
    }

}
