package project.equationinvasion;

import android.widget.TextView;

import java.util.Random;

public class EquationGenerator {

	private static final Random rand = new Random();
	public int expected;
	public int displayed;

	interface Equation {
		void create();
	}

	private TextView equation;
	private TextView answer;
	private int currentLevel;
	private Equation[] equationGen;


	public EquationGenerator() {
		equation = Play.getEquationTextView();
		answer = Play.getAnswerTextView();
		currentLevel = Play.getCurrentLevel();
		equationGen = new Equation[] {
				/**
				 * DO NOT DELETE
				 * Level 1: 0-1
				 * Level 2: 2-3
				 * Level 3: 0-1
				 * Level 4: 4-7
				 * Level 5: 4-19
				 * Level 6: 20-83
				 * DO NOT DELETE
				 */
				new Equation() {//0
					public void create() {
						add();
					}
				},
				new Equation() {//1
					public void create() {
						subtract();
					}
				},
				new Equation() {//2
					public void create() {
						multiply();
					}
				},
				new Equation() {//3
					public void create() {
						divide();
					}
				},
				new Equation() {//4
					public void create() {
						addAdd();
					}
				},
				new Equation() {//5
					public void create() {
						addSubtract();
					}
				},
				new Equation() {//6
					public void create() {
						subtractAdd();
					}
				},
				new Equation() {//7
					public void create() {
						subtractSubtract();
					}
				},
				new Equation() {//8
					public void create() {
						addDivide();
					}
				},
				new Equation() {//9
					public void create() {
						addMultiply();
					}
				},
				new Equation() {//10
					public void create() {
						subtractDivide();
					}
				},
				new Equation() {//11
					public void create() {
						subtractMultiply();
					}
				},
				new Equation() {//12
					public void create() {
						divideAdd();
					}
				},
				new Equation() {//13
					public void create() {
						divideSubtract();
					}
				},
				new Equation() {//14
					public void create() {
						divideDivide();
					}
				},
				new Equation() {//15
					public void create() {
						divideMultiply();
					}
				},
				new Equation() {//16
					public void create() {
						multiplyAdd();
					}
				},
				new Equation() {//17
					public void create() {
						multiplySubtract();
					}
				},
				new Equation() {//18
					public void create() {
						multiplyDivide();
					}
				},
				new Equation() {//19
					public void create() {
						multiplyMultiply();
					}
				},
				new Equation() {//20
					public void create() {
						addAddAdd();
					}
				},
				new Equation() {//21
					public void create() {
						addAddSubtract();
					}
				},
				new Equation() {//22
					public void create() {
						addSubtractAdd();
					}
				},
				new Equation() {//23
					public void create() {
						addSubtractSubtract();
					}
				},
				new Equation() {//24
					public void create() {
						addAddDivide();
					}
				},
				new Equation() {//25
					public void create() {
						addAddMultiply();
					}
				},
				new Equation() {//26
					public void create() {
						addSubtractDivide();
					}
				},
				new Equation() {//27
					public void create() {
						addSubtractMultiply();
					}
				},
				new Equation() {//28
					public void create() {
						addDivideAdd();
					}
				},
				new Equation() {//29
					public void create() {
						addDivideSubtract();
					}
				},
				new Equation() {//30
					public void create() {
						addDivideDivide();
					}
				},
				new Equation() {//31
					public void create() {
						addDivideMultiply();
					}
				},
				new Equation() {///32
					public void create() {
						addMultiplyAdd();
					}
				},
				new Equation() {//33
					public void create() {
						addMultiplySubtract();
					}
				},
				new Equation() {//34
					public void create() {
						addMultiplyDivide();
					}
				},
				new Equation() {//35
					public void create() {
						addMultiplyMultiply();
					}
				},
				new Equation() {//36
					public void create() {
						subtractAddAdd();
					}
				},
				new Equation() {//37
					public void create() {
						subtractAddSubtract();
					}
				},
				new Equation() {//38
					public void create() {
						subtractSubtractAdd();
					}
				},
				new Equation() {//39
					public void create() {
						subtractSubtractSubtract();
					}
				},
				new Equation() {//40
					public void create() {
						subtractAddDivide();
					}
				},
				new Equation() {//41
					public void create() {
						subtractAddMultiply();
					}
				},
				new Equation() {//42
					public void create() {
						subtractSubtractDivide();
					}
				},
				new Equation() {//43
					public void create() {
						subtractSubtractMultiply();
					}
				},
				new Equation() {//44
					public void create() {
						subtractDivideAdd();
					}
				},
				new Equation() {//45
					public void create() {
						subtractDivideSubtract();
					}
				},
				new Equation() {//46
					public void create() {
						subtractDivideDivide();
					}
				},
				new Equation() {//47
					public void create() {
						subtractDivideMultiply();
					}
				},
				new Equation() {//48
					public void create() {
						subtractMultiplyAdd();
					}
				},
				new Equation() {//49
					public void create() {
						subtractMultiplySubtract();
					}
				},
				new Equation() {//50
					public void create() {
						subtractMultiplyDivide();
					}
				},
				new Equation() {//51
					public void create() {
						subtractMultiplyMultiply();
					}
				},
				new Equation() {//52
					public void create() {
						divideAddAdd();
					}
				},
				new Equation() {//53
					public void create() {
						divideAddSubtract();
					}
				},
				new Equation() {//54
					public void create() {
						divideSubtractAdd();
					}
				},
				new Equation() {//55
					public void create() {
						divideSubtractSubtract();
					}
				},
				new Equation() {//56
					public void create() {
						divideAddDivide();
					}
				},
				new Equation() {//57
					public void create() {
						divideAddMultiply();
					}
				},
				new Equation() {//58
					public void create() {
						divideSubtractDivide();
					}
				},
				new Equation() {//59
					public void create() {
						divideSubtractMultiply();
					}
				},
				new Equation() {//60
					public void create() {
						divideDivideAdd();
					}
				},
				new Equation() {//61
					public void create() {
						divideDivideSubtract();
					}
				},
				new Equation() {//62
					public void create() {
						divideDivideDivide();
					}
				},
				new Equation() {//63
					public void create() {
						divideDivideMultiply();
					}
				},
				new Equation() {//64
					public void create() {
						divideMultiplyAdd();
					}
				},
				new Equation() {//65
					public void create() {
						divideMultiplySubtract();
					}
				},
				new Equation() {//66
					public void create() {
						divideMultiplyDivide();
					}
				},
				new Equation() {//67
					public void create() {
						divideMultiplyMultiply();
					}
				},
				new Equation() {//68
					public void create() {
						multiplyAddAdd();
					}
				},
				new Equation() {//69
					public void create() {
						multiplyAddSubtract();
					}
				},
				new Equation() {//70
					public void create() {
						multiplySubtractAdd();
					}
				},
				new Equation() {//71
					public void create() {
						multiplySubtractSubtract();
					}
				},
				new Equation() {//72
					public void create() {
						multiplyAddDivide();
					}
				},
				new Equation() {//73
					public void create() {
						multiplyAddMultiply();
					}
				},
				new Equation() {//74
					public void create() {
						multiplySubtractDivide();
					}
				},
				new Equation() {//75
					public void create() {
						multiplySubtractMultiply();
					}
				},
				new Equation() {//76
					public void create() {
						multiplyDivideAdd();
					}
				},
				new Equation() {//77
					public void create() {
						multiplyDivideSubtract();
					}
				},
				new Equation() {//78
					public void create() {
						multiplyDivideDivide();
					}
				},
				new Equation() {//79
					public void create() {
						multiplyDivideMultiply();
					}
				},
				new Equation() {//80
					public void create() {
						multiplyMultiplyAdd();
					}
				},
				new Equation() {//81
					public void create() {
						multiplyMultiplySubtract();
					}
				},
				new Equation() {//82
					public void create() {
						multiplyMultiplyDivide();
					}
				},
				new Equation() {//83
					public void create() {
						multiplyMultiplyMultiply();
					}
				}
		};
	}

	private void add() {
		int a, b;
		if (currentLevel == 3) {
			a = rand.nextInt(100);
			b = rand.nextInt(100);
		} else {
			a = rand.nextInt(10);
			b = rand.nextInt(10);
		}
		expected = a + b;
		equation.setText(a + " + " + b);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		expected = a + b + c;
		equation.setText(a + " + " + b + " + " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		expected = a + b - c;
		equation.setText(a + " + " + b + " - " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		expected = a + b * c;
		equation.setText(a + " + " + b + " * " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c;
				equation.setText(a + " + " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivide();
			}
		} else {
			addDivide();
		}
	}

	private void addAddAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a + b + c + d;
		equation.setText(a + " + " + b + " + " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addAddSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a + b + c - d;
		equation.setText(a + " + " + b + " + " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addAddDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (c != 0) {
			if (c % d == 0) {
				expected = a + b + c / d;
				equation.setText(a + " + " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addAddDivide();
			}
		} else {
			addAddDivide();
		}
	}

	private void addAddMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a + b + c * d;
		equation.setText(a + " + " + b + " + " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addSubtractAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a + b - c + d;
		equation.setText(a + " + " + b + " - " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addSubtractSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a + b - c - d;
		equation.setText(a + " + " + b + " - " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addSubtractDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a + b - c / d;
				equation.setText(a + " + " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addSubtractDivide();
			}
		} else {
			addSubtractDivide();
		}
	}

	private void addSubtractMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a + b - c * d;
		equation.setText(a + " + " + b + " - " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addDivideAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c + d;
				equation.setText(a + " + " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideAdd();
			}
		} else {
			addDivideAdd();
		}
	}

	private void addDivideSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c - d;
				equation.setText(a + " + " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideSubtract();
			}
		} else {
			addDivideSubtract();
		}
	}

	private void addDivideDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(9) + 1;
		if (c != 0 && d != 0) {
			if (b % c == 0 && (b / c) % d == 0) {
				expected = a + b / c / d;
				equation.setText(a + " + " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideDivide();
			}
		} else {
			addDivideDivide();
		}
	}

	private void addDivideMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(10);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c * d;
				equation.setText(a + " + " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideMultiply();
			}
		} else {
			addDivideMultiply();
		}
	}

	private void addMultiplyAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		expected = a + b * c + d;
		equation.setText(a + " + " + b + " * " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addMultiplySubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		expected = a + b * c - d;
		equation.setText(a + " + " + b + " * " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void addMultiplyDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if ((b * c) % d == 0) {
				expected = a + b * c / d;
				equation.setText(a + " + " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addMultiplyDivide();
			}
		} else {
			addMultiplyDivide();
		}
	}

	private void addMultiplyMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a + b * c * d;
		equation.setText(a + " + " + b + " * " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtract() {
		int a, b;
		if (currentLevel == 3) {
			a = rand.nextInt(100);
			b = rand.nextInt(100);
		} else {
			a = rand.nextInt(10);
			b = rand.nextInt(10);
		}
		expected = a - b;
		equation.setText(a + " - " + b);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		expected = a - b + c;
		equation.setText(a + " - " + b + " + " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		expected = a - b - c;
		equation.setText(a + " - " + b + " - " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		expected = a - b * c;
		equation.setText(a + " - " + b + " * " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c;
				equation.setText(a + " - " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivide();
			}
		} else {
			subtractDivide();
		}
	}

	private void subtractAddAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a - b + c + d;
		equation.setText(a + " - " + b + " + " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractAddSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a - b + c - d;
		equation.setText(a + " - " + b + " + " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractAddDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(9) + 1;
		if (c != 0) {
			if (c % d == 0) {
				expected = a - b + c / d;
				equation.setText(a + " - " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractAddDivide();
			}
		} else {
			subtractAddDivide();
		}
	}

	private void subtractAddMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a - b + c * d;
		equation.setText(a + " - " + b + " + " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractSubtractAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a - b - c + d;
		equation.setText(a + " - " + b + " - " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractSubtractSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a - b - c - d;
		equation.setText(a + " - " + b + " - " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractSubtractDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a - b - c / d;
				equation.setText(a + " - " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractSubtractDivide();
			}
		} else {
			subtractSubtractDivide();
		}
	}

	private void subtractSubtractMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a - b - c * d;
		equation.setText(a + " - " + b + " - " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractDivideAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c + d;
				equation.setText(a + " - " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideAdd();
			}
		} else {
			subtractDivideAdd();
		}
	}

	private void subtractDivideSubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c - d;
				equation.setText(a + " - " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideSubtract();
			}
		} else {
			subtractDivideSubtract();
		}
	}

	private void subtractDivideDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(9) + 1;
		if (c != 0 && d != 0) {
			if (b % c == 0 && (b / c) % d == 0) {
				expected = a - b / c / d;
				equation.setText(a + " - " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideDivide();
			}
		} else {
			subtractDivideDivide();
		}
	}

	private void subtractDivideMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(10);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c * d;
				equation.setText(a + " - " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideMultiply();
			}
		} else {
			subtractDivideMultiply();
		}
	}

	private void subtractMultiplyAdd() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		expected = a - b * c + d;
		equation.setText(a + " - " + b + " * " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractMultiplySubtract() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		expected = a - b * c - d;
		equation.setText(a + " - " + b + " * " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void subtractMultiplyDivide() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if ((b * c) % d == 0) {
				expected = a - b * c / d;
				equation.setText(a + " - " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractMultiplyDivide();
			}
		} else {
			subtractMultiplyDivide();
		}
	}

	private void subtractMultiplyMultiply() {
		int a = rand.nextInt(100);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a - b * c * d;
		equation.setText(a + " - " + b + " * " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void divide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b;
				equation.setText(a + " / " + b);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divide();
			}
		} else {
			divide();
		}
	}

	private void divideAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c;
				equation.setText(a + " / " + b + " + " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAdd();
			}
		} else {
			divideAdd();
		}

	}

	private void divideSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c;
				equation.setText(a + " / " + b + " - " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtract();
			}
		} else {
			divideSubtract();
		}
	}

	private void divideMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c;
				equation.setText(a + " / " + b + " * " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiply();
			}
		} else {
			divideMultiply();
		}
	}

	private void divideDivide() {
		int a = rand.nextInt(9) + 1;
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(9) + 1;
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c;
				equation.setText(a + " / " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivide();
			}
		} else {
			divideDivide();
		}
	}

	private void divideAddAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c + d;
				equation.setText(a + " / " + b + " + " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddAdd();
			}
		} else {
			divideAddAdd();
		}
	}

	private void divideAddSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c - d;
				equation.setText(a + " / " + b + " + " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddSubtract();
			}
		} else {
			divideAddSubtract();
		}
	}

	private void divideAddDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (b != 0 && d != 0) {
			if (a % b == 0 && c % d == 0) {
				expected = a / b + c / d;
				equation.setText(a + " / " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddDivide();
			}
		} else {
			divideAddDivide();
		}
	}

	private void divideAddMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c * d;
				equation.setText(a + " / " + b + " + " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddMultiply();
			}
		} else {
			divideAddMultiply();
		}
	}

	private void divideSubtractAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c + d;
				equation.setText(a + " / " + b + " - " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractAdd();
			}
		} else {
			divideSubtractAdd();
		}
	}

	private void divideSubtractSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c - d;
				equation.setText(a + " / " + b + " - " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractSubtract();
			}
		} else {
			divideSubtractSubtract();
		}
	}

	private void divideSubtractDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (b != 0 && d != 0) {
			if (a % b == 0 && c % d == 0) {
				expected = a / b - c / d;
				equation.setText(a + " / " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractDivide();
			}
		} else {
			divideSubtractDivide();
		}
	}

	private void divideSubtractMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c * d;
				equation.setText(a + " / " + b + " - " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractMultiply();
			}
		} else {
			divideSubtractMultiply();
		}
	}

	private void divideDivideAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c + d;
				equation.setText(a + " / " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideAdd();
			}
		} else {
			divideDivideAdd();
		}
	}

	private void divideDivideSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c - d;
				equation.setText(a + " / " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideSubtract();
			}
		} else {
			divideDivideSubtract();
		}
	}

	private void divideDivideDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(9) + 1;
		if (b != 0 && c != 0 && d != 0) {
			if (a % b == 0 && (a / b) % c == 0 && (a / b / c) % d == 0) {
				expected = a / b / c / d;
				equation.setText(a + " / " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideDivide();
			}
		} else {
			divideDivideDivide();
		}
	}

	private void divideDivideMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(10);
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c * d;
				equation.setText(a + " / " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideMultiply();
			}
		} else {
			divideDivideMultiply();
		}
	}

	private void divideMultiplyAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c + d;
				equation.setText(a + " / " + b + " * " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplyAdd();
			}
		} else {
			divideMultiplyAdd();
		}
	}

	private void divideMultiplySubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c - d;
				equation.setText(a + " / " + b + " * " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplySubtract();
			}
		} else {
			divideMultiplySubtract();
		}
	}

	private void divideMultiplyDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (b != 0 && d != 0) {
			if (a % b == 0 && (a / b * c) % d == 0) {
				expected = a / b * c / d;
				equation.setText(a + " / " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplySubtract();
			}
		} else {
			divideMultiplySubtract();
		}
	}

	private void divideMultiplyMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(9) + 1;
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c * d;
				equation.setText(a + " / " + b + " * " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplyMultiply();
			}
		} else {
			divideMultiplyMultiply();
		}
	}

	private void multiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		expected = a * b;
		equation.setText(a + " * " + b);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		expected = a * b + c;
		equation.setText(a + " * " + b + " + " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplySubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		expected = a * b - c;
		equation.setText(a + " * " + b + " - " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c;
				equation.setText(a + " * " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivide();
			}
		} else {
			multiplyDivide();
		}
	}

	private void multiplyMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		expected = a * b * c;
		equation.setText(a + " * " + b + " * " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyAddAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a * b + c + d;
		equation.setText(a + " * " + b + " + " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyAddSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a * b + c - d;
		equation.setText(a + " * " + b + " + " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyAddDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a * b + c / d;
				equation.setText(a + " * " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyAddDivide();
			}
		} else {
			multiplyAddDivide();
		}
	}

	private void multiplyAddMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a * b + c * d;
		equation.setText(a + " * " + b + " + " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplySubtractAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a * b - c + d;
		equation.setText(a + " * " + b + " - " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplySubtractSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(100);
		int d = rand.nextInt(100);
		expected = a * b - c - d;
		equation.setText(a + " * " + b + " - " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplySubtractDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a * b - c / d;
				equation.setText(a + " * " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplySubtractDivide();
			}
		} else {
			multiplySubtractDivide();
		}
	}

	private void multiplySubtractMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a * b - c * d;
		equation.setText(a + " * " + b + " - " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyDivideAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c + d;
				equation.setText(a + " * " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideAdd();
			}
		} else {
			multiplyDivideAdd();
		}
	}

	private void multiplyDivideSubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(100);
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c - d;
				equation.setText(a + " * " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideSubtract();
			}
		} else {
			multiplyDivideSubtract();
		}
	}

	private void multiplyDivideDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(9) + 1;
		if (c != 0 && d != 0) {
			if ((a * b) % c == 0 && (a * b / c) % d == 0) {
				expected = a * b / c / d;
				equation.setText(a + " * " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideDivide();
			}
		} else {
			multiplyDivideDivide();
		}
	}

	private void multiplyDivideMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(9) + 1;
		int d = rand.nextInt(10);
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c * d;
				equation.setText(a + " * " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideMultiply();
			}
		} else {
			multiplyDivideMultiply();
		}
	}

	private void multiplyMultiplyAdd() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		expected = a * b * c + d;
		equation.setText(a + " * " + b + " * " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyMultiplySubtract() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(100);
		expected = a * b * c - d;
		equation.setText(a + " * " + b + " * " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	private void multiplyMultiplyDivide() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if ((a * b * c) % d == 0) {
				expected = a * b * c / d;
				equation.setText(a + " * " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyMultiplyDivide();
			}
		} else {
			multiplyMultiplyDivide();
		}
	}

	private void multiplyMultiplyMultiply() {
		int a = rand.nextInt(10);
		int b = rand.nextInt(10);
		int c = rand.nextInt(10);
		int d = rand.nextInt(10);
		expected = a * b * c * d;
		equation.setText(a + " * " + b + " * " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}
	
	private boolean correctAnswerShown;
	
	/**
	 * Random Answer Generator.
	 *
	 * @param answer from equation
	 * @return displayed
	 */
	private int answerGen(int answer) {
		int wrong;
		boolean correctAnswerShown = rand.nextBoolean();  // True or False Decision

		if (answer >= -10 && answer <= 10) {
			if (!correctAnswerShown) {
				int variable = 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else if (answer >= -20 && answer <= 20) {
			if (!correctAnswerShown) {
				int variable = rand.nextInt(2) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else if (answer >= -40 && answer <= 40) {
			if (!correctAnswerShown) {
				int variable = rand.nextInt(3) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else if (answer >= -60 && answer <= 60) {
			if (!correctAnswerShown) {
				int variable = rand.nextInt(4) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else {
			if (!correctAnswerShown) {
				int variable = rand.nextInt(5) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		}
		return displayed;
	}

	public void generate(int currentLevel) {
		this.currentLevel = currentLevel;
		int index;
		switch(currentLevel) {
			case 1:
			case 3:
				//0-1
				index = rand.nextInt(2);
				break;
			case 2:
				//2-3
				index = rand.nextInt(2) + 2;
				break;
			case 4:
				//4-7
				index = rand.nextInt(4) + 4;
				break;
			case 5:
				//4-19
				index = rand.nextInt(16) + 4;
				break;
			case 6:
				//20-83
				index = rand.nextInt(64) + 20;
				break;
			default:
				//0-1
				index = rand.nextInt(2);
				break;
		}
		equationGen[index].create();
	}

	public int getEquation() {
		return expected;
	}

	public int getAnswer() {
		return displayed;
	}
}