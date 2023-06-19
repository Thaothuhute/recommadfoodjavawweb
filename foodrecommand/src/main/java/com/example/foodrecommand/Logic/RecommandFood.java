package com.example.foodrecommand.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.Datatrain;
import com.example.foodrecommand.Service.DataService;

import jakarta.servlet.http.PushBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class RecommandFood {
    private HashMap<String,Integer> answer;
    private List<Datatrain> datatrains;

   

    public Node returnTrain(){
        List<Datatrain> dbTRain =  new ArrayList<>(); 
        dbTRain =datatrains;
        Train trainEnd =  new Train(dbTRain);
        Node root = new Node();
        trainEnd.data = dbTRain;
        root = trainEnd.BuidDecisionTree(trainEnd.AddData());
        trainEnd.data = dbTRain;
        return catchID3(root, answer);


    }

    public Node catchID3(Node node, HashMap<String,Integer> answer){
        if(node.attribute  == null){
            return node;
        }
        else{
            int ValueAns = answer.get(node.attribute);
            Node nodechill = (Node) node.children.stream().filter(n-> n.value == ValueAns).findFirst().orElse(null);
            return catchID3(nodechill, answer);
        }
    }
    public RecommandFood(HashMap<String,Integer> anHashMap){
        this.answer = anHashMap;    
    }
}
