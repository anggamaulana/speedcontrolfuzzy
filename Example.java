import java.util.Arrays;

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

	public static void main(String[] args) throws InterruptedException {
		Engine engine = new Engine();
		engine.setName("speed control");

		InputVariable inputVariable1 = new InputVariable();
		inputVariable1.setEnabled(true);
		inputVariable1.setName("temperature");
		inputVariable1.setRange(0.000, 100.000);
		inputVariable1.addTerm(new Trapezoid("dingin", 0.000, 0.000, 15.000,
				22.000));
		inputVariable1.addTerm(new Triangle("sejuk", 15.000, 25.000, 30.000));
		inputVariable1.addTerm(new Triangle("hangat", 25.000, 40.000, 55.000));
		inputVariable1.addTerm(new Trapezoid("panas", 40.000, 60.000, 100.000,
				100.000));
		engine.addInputVariable(inputVariable1);

		InputVariable inputVariable2 = new InputVariable();
		inputVariable2.setEnabled(true);
		inputVariable2.setName("kemacetan");
		inputVariable2.setRange(0.000, 30.000);
		inputVariable2.addTerm(new Trapezoid("lengang", 0.000, 0.000, 5.000,
				10.000));
		inputVariable2.addTerm(new Triangle("sedang", 5.000, 15.000, 25.000));
		inputVariable2.addTerm(new Trapezoid("padat", 15.000, 25.000, 30.000,
				30.000));
		engine.addInputVariable(inputVariable2);

		InputVariable inputVariable3 = new InputVariable();
		inputVariable3.setEnabled(true);
		inputVariable3.setName("bahanbakar");
		inputVariable3.setRange(0.000, 50.000);
		inputVariable3.addTerm(new Trapezoid("kosong", 0.000, 0.000, 5.000,
				10.000));
		inputVariable3.addTerm(new Triangle("sedang", 5.000, 18.000, 28.000));
		inputVariable3.addTerm(new Trapezoid("penuh", 18.000, 38.000, 50.000,
				50.000));
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
		outputVariable.addTerm(new Trapezoid("lambat", 0.000, 0.000, 25.000,
				50.000));
		outputVariable.addTerm(new Triangle("sedang", 40.000, 70.000, 100.000));
		outputVariable.addTerm(new Trapezoid("cepat", 70.000, 110.000, 200.000,
				200.000));
		engine.addOutputVariable(outputVariable);

		RuleBlock ruleBlock = new RuleBlock();
		ruleBlock.setEnabled(true);
		ruleBlock.setName("");
		ruleBlock.setConjunction(new Minimum());
		ruleBlock.setDisjunction(new Maximum());
		ruleBlock.setActivation(new Minimum());
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is sedang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is dingin and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is sedang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is sejuk and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is sedang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is hangat and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is lengang and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is lengang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is lengang and bahanbakar is penuh then kecepatan is cepat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is sedang and bahanbakar is kosong then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is sedang and bahanbakar is sedang then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is sedang and bahanbakar is penuh then kecepatan is sedang",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is padat and bahanbakar is kosong then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is padat and bahanbakar is sedang then kecepatan is lambat",
								engine));
		ruleBlock
				.addRule(Rule
						.parse("if temperature is panas and kemacetan is padat and bahanbakar is penuh then kecepatan is lambat",
								engine));
		engine.addRuleBlock(ruleBlock);

		// engine.configure("", "", "Minimum", "Maximum", "Centroid");

		StringBuilder status = new StringBuilder();
		if (!engine.isReady(status)) {
			throw new RuntimeException("Engine not ready. "
					+ "The following errors were encountered:\n"
					+ status.toString());
		}

		for (int i = 0; i < 100; ++i) {
			// double light = ambient.getMinimum() + i * (ambient.range() / 50);
			double temp, kem, fuel;
			if (args.length < 3) {
				temp = Math.random() * 100;
				kem = Math.random() * 30;
				fuel = Math.random() * 50;
			} else {
				temp = Double.parseDouble(args[0]);
				kem = Double.parseDouble(args[1]);
				fuel = Double.parseDouble(args[2]);
			}

			inputVariable1.setInputValue(temp);
			inputVariable2.setInputValue(kem);
			inputVariable3.setInputValue(fuel);
			engine.process();
			FuzzyLite
					.logger()
					.info(String
							.format("\nTemperature.input = %s Celcius \n Kemacetan.input = %s Kendaraan/Menit\n BahanBakar.input = %s Liter \n\n\n-> Kecepatan.output(centroid) = %s Km/h",
									Op.str(temp), Op.str(kem), Op.str(fuel),
									Op.str(outputVariable.getOutputValue())));

			Fuzzy f = new Fuzzy();
			// fuzzifikasi
			// input : temperature:20,kemacetan:5,bahanbakar:20
			// output : nilai fuzzy setiap rule misal if x is a and x2 is b and
			// x3 is c maka
			// r1:[0.2, 0.3, 0], r2:[0.3, 0.4, 0], dst sejumlah baris rules
			double[][] a = f.fuzzifikasi(temp, kem, fuel);

			// inference
			// input : temperature[0.9, 0.2, 0, 0] kemacetan[0.9, 0.2, 0]
			// bahanbakar[0.9, 0.2, 0]
			// output : kecepatan[-1,0.4]
			double[] b = f.inference(a);

			// defuzzifikasi
			// input : kecepatan[-1, 0.4]
			// process : MoM(-1,0.4)
			// output : 100km/h
			double hasil = f.defuzzifier(b);
			// System.out.println(Arrays.toString(b));
			System.out.println("-> Kecepatan.output (MoM) = "
					+ String.valueOf(hasil) + " Km/h\n=============");

			if (args.length >= 3) {
				break;
			}
			Thread.sleep(4000);
		}

	}

	public static class Fuzzy {

		public Fuzzy() {

		}

		double[][] fuzzifikasi(double temp, double kemacetan, double bensin) {
			double[][] result = new double[rule.length][3];
			// fuzzifikasi all rules
			for (int i = 0; i < rule.length; i++) {

				result[i][0] = getFuzzyValue(rule[i][0], temp);
				result[i][1] = getFuzzyValue(rule[i][1], kemacetan);
				result[i][2] = getFuzzyValue(rule[i][2], bensin);
			}
			return result;
		}

		double[] inference(double[][] fuzzifikasi) {
			double[] result = new double[2];

			double[] temp = new double[fuzzifikasi.length];
			for (int i = 0; i < fuzzifikasi.length; i++) {
				temp[i] = Math.min(
						Math.min(fuzzifikasi[i][0], fuzzifikasi[i][1]),
						fuzzifikasi[i][2]);
			}

			// finding maximum
			double max = 0;
			int index = 0;
			for (int j = 0; j < temp.length; j++) {
				if (temp[j] > max) {
					max = temp[j];
					index = j;
				}
			}

			result[0] = rule[index][3];
			result[1] = max;

			return result;
		}

		double defuzzifier(double[] inference) {
			double result = 0;
			int jenis = (int) inference[0];
			double fuzzyVal = inference[1];
			// using mom

			if (jenis == -1) {
				// jika lambat
				// cari batas min dan max
				double min = 0;
				double max = miringKanan2(25, 50, fuzzyVal);
				result = ((0.5 * Math.pow(max, 2)) - (0.5 * Math.pow(min, 2)))
						/ (max - min);

			} else if (jenis == -2) {
				// jika sedang
				// cari batas min max
				double min = miringKiri2(40, 70, fuzzyVal);
				double max = miringKanan2(70, 100, fuzzyVal);
				result = ((0.5 * Math.pow(max, 2)) - (0.5 * Math.pow(min, 2)))
						/ (max - min);

			} else if (jenis == -3) {
				// jika cepat
				// cari batas min max
				double min = miringKiri2(70, 110, fuzzyVal);
				double max = 200;
				result = ((0.5 * Math.pow(max, 2)) - (0.5 * Math.pow(min, 2)))
						/ (max - min);

			}

			return result;
		}

		int[][] rule = new int[][] { { 1, 5, 8, -1 }, { 1, 5, 9, -2 },
				{ 1, 5, 10, -3 }, { 1, 6, 8, -1 }, { 1, 6, 9, -2 },
				{ 1, 6, 10, -2 }, { 1, 7, 8, -1 }, { 1, 7, 9, -1 },
				{ 1, 7, 10, -1 }, { 2, 5, 8, -1 }, { 2, 5, 9, -2 },
				{ 2, 5, 10, -3 }, { 2, 6, 8, -1 }, { 2, 6, 9, -2 },
				{ 2, 6, 10, -2 }, { 2, 7, 8, -1 }, { 2, 7, 9, -1 },
				{ 2, 7, 10, -1 }, { 3, 5, 8, -1 }, { 3, 5, 9, -2 },
				{ 3, 5, 10, -3 }, { 3, 6, 8, -1 }, { 3, 6, 9, -2 },
				{ 3, 6, 10, -2 }, { 3, 7, 8, -1 }, { 3, 7, 9, -1 },
				{ 3, 7, 10, -1 }, { 4, 5, 8, -1 }, { 4, 5, 9, -2 },
				{ 4, 5, 10, -3 }, { 4, 6, 8, -2 }, { 4, 6, 9, -2 },
				{ 4, 6, 10, -2 }, { 4, 7, 8, -1 }, { 4, 7, 9, -1 },
				{ 4, 7, 10, -1 } };

		double getFuzzyValue(int type, double x) {
			switch (type) {
			case 1:
				return dingin(x);

			case 2:
				return sejuk(x);
			case 3:
				return hangat(x);
			case 4:
				return panas(x);
			case 5:
				return lengang(x);
			case 6:
				return sedang_macet(x);
			case 7:
				return padat(x);
			case 8:
				return kosong(x);
			case 9:
				return sedang_bensin(x);
			case 10:
				return penuh(x);

			}
			return 0;
		}

		int getFuzzyLinguistic(int type) {
			if (type >= 1 && type <= 4) {
				return 1;
			} else if (type >= 5 && type <= 7) {
				return 2;
			} else if (type >= 8 && type <= 10) {
				return 3;
			} else {
				return 0;
			}
		}

		// MEMBERSHIP FUNCTION
		// BEGIN----------------------------------------------------------------
		double miringKanan(double batasMax, double batasMin, double x) {
			return (batasMax - x) / (batasMax - batasMin);
		}

		double miringKiri(double batasMax, double batasMin, double x) {
			return (x - batasMin) / (batasMax - batasMin);
		}

		double miringKanan2(double batasMax, double batasMin, double y) {
			return batasMax - (y * (batasMax - batasMin));
		}

		double miringKiri2(double batasMax, double batasMin, double y) {
			return (y * (batasMax - batasMin)) + batasMin;
		}

		double dingin(double x) {
			double result = 0;
			if (x <= 15) {
				result = 1;
			} else if (x > 15 && x <= 22) {
				result = miringKanan(15, 22, x);
			}
			return result;

		}

		double sejuk(double x) {
			double result = 0;
			if (x >= 15 && x <= 25) {
				result = miringKiri(15, 25, x);
			} else if (x >= 24 && x <= 30) {
				result = miringKanan(25, 30, x);
			}
			return result;

		}

		double hangat(double x) {
			double result = 0;
			if (x >= 25 && x <= 40) {
				result = miringKiri(25, 40, x);
			} else if (x >= 41 && x <= 55) {
				result = miringKanan(40, 55, x);
			}
			return result;

		}

		double panas(double x) {
			double result = 0;
			if (x > 60) {
				result = 1;
			} else if (x >= 40 && x <= 60) {
				result = miringKiri(40, 60, x);
			}
			return result;

		}

		double lengang(double x) {
			double result = 0;
			if (x <= 5) {
				result = 1;
			} else if (x > 5 && x <= 10) {
				result = miringKanan(5, 10, x);
			}
			return result;

		}

		double sedang_macet(double x) {
			double result = 0;
			if (x >= 5 && x <= 15) {
				result = miringKiri(5, 15, x);
			} else if (x >= 16 && x <= 25) {
				result = miringKanan(15, 25, x);
			}
			return result;

		}

		double padat(double x) {
			double result = 0;
			if (x > 25) {
				result = 1;
			} else if (x >= 15 && x <= 25) {
				result = miringKiri(15, 25, x);
			}
			return result;

		}

		double kosong(double x) {
			double result = 0;
			if (x <= 5) {
				result = 1;
			} else if (x > 5 && x <= 10) {
				result = miringKanan(5, 10, x);
			}
			return result;

		}

		double sedang_bensin(double x) {
			double result = 0;
			if (x >= 5 && x <= 18) {
				result = miringKiri(5, 18, x);
			} else if (x >= 19 && x <= 28) {
				result = miringKanan(18, 28, x);
			}
			return result;

		}

		double penuh(double x) {
			double result = 0;
			if (x > 38) {
				result = 1;
			} else if (x >= 18 && x <= 38) {
				result = miringKiri(15, 25, x);
			}
			return result;

		}
		// MEMBERSHIP FUNCTION
		// END----------------------------------------------------------------

	}

}
