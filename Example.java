import com.fuzzylite.*;
import com.fuzzylite.defuzzifier.*;
import com.fuzzylite.factory.*;
import com.fuzzylite.hedge.*;
import com.fuzzylite.imex.*;
import com.fuzzylite.norm.*;
import com.fuzzylite.norm.s.*;
import com.fuzzylite.norm.t.*;
import com.fuzzylite.rule.*;
import com.fuzzylite.term.*;
import com.fuzzylite.variable.*;



public class Example {

    public static void main(String[] args) throws InterruptedException{
        Engine engine = new Engine();
        engine.setName("speed control");

        InputVariable inputVariable1 = new InputVariable();
        inputVariable1.setEnabled(true);
        inputVariable1.setName("temperature");
        inputVariable1.setRange(0.000, 100.000);
        inputVariable1.addTerm(new Trapezoid("dingin", 0.000, 0.000, 15.000, 22.000));
        inputVariable1.addTerm(new Triangle("sejuk", 15.000, 25.000, 30.000));
        inputVariable1.addTerm(new Triangle("hangat", 25.000, 40.000, 55.000));
        inputVariable1.addTerm(new Trapezoid("panas", 40.000, 60.000, 100.000, 100.000));
        engine.addInputVariable(inputVariable1);

        InputVariable inputVariable2 = new InputVariable();
        inputVariable2.setEnabled(true);
        inputVariable2.setName("kemacetan");
        inputVariable2.setRange(0.000, 30.000);
        inputVariable2.addTerm(new Trapezoid("lengang", 0.000, 0.000, 5.000, 10.000));
        inputVariable2.addTerm(new Triangle("sedang", 5.000, 15.000, 25.000));
        inputVariable2.addTerm(new Trapezoid("padat", 15.000, 25.000, 30.000, 30.000));
        engine.addInputVariable(inputVariable2);

        InputVariable inputVariable3 = new InputVariable();
        inputVariable3.setEnabled(true);
        inputVariable3.setName("bahanbakar");
        inputVariable3.setRange(0.000, 50.000);
        inputVariable3.addTerm(new Trapezoid("kosong", 0.000, 0.000, 5.000, 10.000));
        inputVariable3.addTerm(new Triangle("sedang", 5.000, 18.000, 28.000));
        inputVariable3.addTerm(new Trapezoid("penuh", 18.000, 38.000, 50.000, 50.000));
        engine.addInputVariable(inputVariable3);

        OutputVariable outputVariable = new OutputVariable();
        outputVariable.setEnabled(true);
        outputVariable.setName("kecepatan");
        outputVariable.setRange(0.000, 200.000);
        outputVariable.fuzzyOutput().setAccumulation(new DrasticSum());
        outputVariable.setDefuzzifier(new Centroid(200));
        outputVariable.setDefaultValue(Double.NaN);
        // outputVariable.setLockValidOutput(false);
        // outputVariable.setLockOutputRange(false);
        outputVariable.addTerm(new Trapezoid("lambat", 0.000, 0.000, 25.000, 50.000));
        outputVariable.addTerm(new Triangle("sedang", 40.000, 70.000, 100.000));
        outputVariable.addTerm(new Trapezoid("cepat", 70.000, 110.000, 200.000, 200.000));
        engine.addOutputVariable(outputVariable);

        RuleBlock ruleBlock = new RuleBlock();
        ruleBlock.setEnabled(true);
        ruleBlock.setName("");
        ruleBlock.setConjunction(new Minimum());
        ruleBlock.setDisjunction(new Maximum());
        ruleBlock.setActivation(new Minimum());
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is sedang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is dingin and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is sedang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is sejuk and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is sedang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is hangat and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is sedang and bahanbakar is kosong then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat", engine));
        ruleBlock.addRule(Rule.parse("if temperature is panas and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat", engine));
        engine.addRuleBlock(ruleBlock);


        // engine.configure("", "", "Minimum", "Maximum", "Centroid");

        StringBuilder status = new StringBuilder();
        if (!engine.isReady(status)) {
            throw new RuntimeException("Engine not ready. "
                    + "The following errors were encountered:\n" + status.toString());
        }

        for (int i = 0; i < 50; ++i) {
        //     double light = ambient.getMinimum() + i * (ambient.range() / 50);
            double temp=Math.random()*100;
            double kem=Math.random()*30;
            double fuel=Math.random()*50;

            inputVariable1.setInputValue(temp);
            inputVariable2.setInputValue(kem);
            inputVariable3.setInputValue(fuel);
            engine.process();
            FuzzyLite.logger().info(String.format(
                    "Temperature.input = %s \n Kemacetan.input = %s \n BahanBakar.input = %s \n-> Kecepatan.output = %s\n\n=============",
                    Op.str(temp),Op.str(kem),Op.str(fuel), Op.str(outputVariable.getOutputValue())));
             Thread.sleep(2000);
        }
    }
}