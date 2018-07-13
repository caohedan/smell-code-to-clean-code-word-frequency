import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {
        Map<String, List<Input>> map = groupInputWithKeyword(inputStr);
        List<Word> resultModel = translateToModel(map);
        resultModel.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        return buildResult(resultModel).toString();


    }

    private StringJoiner buildResult(List<Word> resultModel) {
        StringJoiner resultView = new StringJoiner("\n");
        for (Word w : resultModel) {
            String s = w.getValue() + " " + w.getWordCount();
            resultView.add(s);
        }
        return resultView;
    }

    private List<Word> translateToModel(Map<String, List<Input>> map) {
        List<Word> resultModel = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word input = new Word(entry.getKey(), entry.getValue().size());
            resultModel.add(input);
        }
        return resultModel;
    }

    private Map<String, List<Input>> groupInputWithKeyword(String inputStr) {
        //split the input string with 1 to n pieces of spaces
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s);
            inputList.add(input);
        }

        //get the map for the next step of sizing the same word
        return getListMap(inputList);
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
