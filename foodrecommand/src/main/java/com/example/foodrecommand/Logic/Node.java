package com.example.foodrecommand.Logic;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public String attribute;
    public int value;
    public List<Integer> Label = new ArrayList<>();
    public List<String> atributeChecked;
    public List<Node> children;
    public  Node(){
        children = new ArrayList<Node>();
    }
}
