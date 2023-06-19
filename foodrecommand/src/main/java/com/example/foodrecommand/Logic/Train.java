package com.example.foodrecommand.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import com.example.foodrecommand.Model.Datatrain;
import com.example.foodrecommand.Service.DataService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Train {

    @Autowired
    public List<Datatrain> data;
    public List<String> Attribute = new ArrayList<>(List.of(
            "GENDER",
            "AGE",
            "ACTIVE",
            "COLOR",
            "KEYTASTE",
            "KEYEMOTION"));

    public Node rootEnd = new Node();

    public Train(List<Datatrain> data) {
        this.data = data;
    }

    public List<Map<String, Integer>> AddData() {
        List<Map<String, Integer>> Datatrain = new ArrayList<>();
        List<Datatrain> newdata = new ArrayList<>();
       

        for (Datatrain item : data) {
            Map<String, Integer> itemData = new HashMap<>();
            itemData.put("GENDER", item.getGender());
            itemData.put("AGE", item.getAge());
            itemData.put("ACTIVE", item.getActive());
            itemData.put("COLOR", item.getColor());
            itemData.put("KEYEMOTION", item.getKeyemotion());
            itemData.put("KEYTASTE", item.getKeytaste());
            itemData.put("NutributionId", (int) item.getNutribute().getIdNutribute());
            Datatrain.add(itemData);

        }
        return Datatrain;
    }

    public double informationGain(List<Map<String, Integer>> data, String Attributes) {
        double originalEntropy = Entropy(data);
        Map<Integer, List<Map<String, Integer>>> splitData = SplitData(data, Attributes);

        double WeightEntropy = 0;
        for (Integer value : splitData.keySet()) {
            double subsetEntro = Entropy(splitData.get(value));
            double subsetWeigh = (double) splitData.get(value).size() / data.size();
            WeightEntropy += subsetEntro * subsetWeigh;
        }
        double informationGain = originalEntropy - WeightEntropy;

        return informationGain;
    }

    public Map<Integer, List<Map<String, Integer>>> SplitData(List<Map<String, Integer>> data, String Attributes) {
        Map<Integer, List<Map<String, Integer>>> splitdata = new HashMap<>();
        for (Map<String, Integer> item : data) {
            Integer value = item.get(Attributes);

            if (splitdata.containsKey(value)) {
                splitdata.get(value).add(item);
            } else {
                splitdata.put(value, new ArrayList<>(List.of(item)));
            }
        }
        return splitdata;
    }

    public Node BuidDecisionTree(List<Map<String, Integer>> data) {
        Node root = new Node();
        List<String> attribute = new ArrayList<>();

        Map<String, Integer> foodfirst = data.get(0);
        int check;

        if (data.size() > 1) {
            check = 0;
            for (Map<String, Integer> item : data) {
                for (String nameCol : item.keySet()) {
                    if (!nameCol.equals("NutributionId")) {
                        if (!foodfirst.get(nameCol).equals(item.get(nameCol))) {
                            check++;
                        }
                    }
                }
                if (check > 0) {
                    break;
                }
            }
            if (check == 0) {
                for (Map<String, Integer> item : data) {
                    root.Label.add(item.get("NutributionId"));
                }
                return root;
            }
        }

        for (String attributeOne : Attribute) {
            if (data.stream().map(x -> x.get(attributeOne)).distinct().count() != 1) {
                attribute.add(attributeOne);
            }
        }

        if (data.stream().map(x -> x.get("NutributionId")).distinct().count() == 1) {
            root.Label.add(data.get(0).get("NutributionId"));
            return root;
        }

        if (attribute.size() == 0) {
            root.Label.add(data.stream()
            .collect(Collectors.groupingBy(x -> x.get("NutributionId"), counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .get().getKey());
            return root;
        }

        String bestAttribute = "";
        double bestInformationGain = 0;
        for (String attributes : attribute) {
            double informationGainn = informationGain(data, attributes);
            if (informationGainn >= bestInformationGain) {
                bestAttribute = attributes;
                bestInformationGain = informationGainn;
            }
        }

        root.attribute = bestAttribute;

        if (!Attribute.contains(bestAttribute)) {
            String erro = "sdsd";
        }
        attribute.remove(bestAttribute);

        Map<Integer, List<Map<String, Integer>>> splitData = SplitData(data, bestAttribute);

        for (Integer value : splitData.keySet()) {
            Node child = BuidDecisionTree(splitData.get(value));
            child.value = value;
            root.children.add(child);
        }

        return root;
    }

    public double Entropy(List<Map<String, Integer>> data) {
        Map<Integer, Integer> classCounts = new HashMap<>();
        for (Map<String, Integer> item : data) {
            Integer classification = item.get("NutributionId");
            classCounts.put(classification, classCounts.getOrDefault(classification, 0) + 1);
        }

        double entropy = 0;
        for (Integer count : classCounts.values()) {
            double probali = (double) count / data.size();
            entropy -= probali * Math.log(probali) / Math.log(2);
        }

        return entropy;
    }

}
