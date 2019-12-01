package ru.nsu.cw.quadratures;

/**
 * Студинский_18141_1-2_квадратуры
 * Программа была написана для работы с квадратурными формулами
 *
 * @author IvanStudinskii
 */
public enum Quadratures {
    SIN {
        public double getValue(double x) {
            return Math.sin(x);
        }
        public double getArea() {
            return 2 / Math.PI;
        }
    },
    SQRT {
        public double getValue(double x) {
            return Math.sqrt(x);
        }
        public double getArea() {
            return 2. / 3;
        }
    };




    /**
     * Метод, который позволяет получить значение математической функции, вызывающей данный метод
     * @param x - аргумент функции
     * @return значение вызывающей функции в точке x
     */
    public abstract double getValue(double x);

    /**
     * Метод позволяет получить площадь функции, которая его вызвала, на отрезке [-1;1]
     * @return площадь функции, вызвавшей данный метод, на отрезке [-1;1]
     */
    public abstract double getArea();




    /**
     * Метод трапеций. Делит отрезок от lowerLimit до upperLimit на несколько равных частей с шагом step
     * и на каждом отрезке считает площадь трапеции, приближающей функцию, которая вызвала данный метод
     * @param step - шаг
     * @param lowerLimit - нижняя граница интеграла
     * @param upperLimit - верхняя граница интеграла
     * @return приближённая площадь графика функции, которая вызывает этот метод, на интервале [lowerLimit; upperLimit]
     */
    public double trapezoidMethod(double step, double lowerLimit, double upperLimit) {
        double result = 0;
        for (double i = lowerLimit; i < upperLimit; i += step) {
            result += trapezoid(i, i + step);
        }

        return result;
    }

    /**
     * Метод Симпсона. Делит отрезок от lowerLimit до upperLimit на несколько равных частей с шагом step
     * и на каждом отрезке считает площадь графика многочлена второй степени, приближающего функцию, которая
     * вызвала данный метод
     * @param step - шаг
     * @param lowerLimit - левая граница
     * @param upperLimit - правая гранциа
     * @return приближённая площадь графика функции, которая вызывает этот метод, на интервале [lowerLimit; upperLimit]
     */
    public double simpsonMethod(double step, double lowerLimit, double upperLimit) {
        double result = 0;
        for (double i = lowerLimit; i < upperLimit; i += step) {
            result += quadraticPolynomial(i, i + step);
        }

        return result;
    }

    /**
     * Формула Гаусса по трём узлам. Делит отрезок от lowerLimit до upperLimit на несколько равных частей с шагом step
     * и на каждом отрезке считает приближённую площадь функции, которая вызвала данный метод
     * @param step - шаг
     * @param lowerLimit - левая граница
     * @param upperLimit - правая гранциа
     * @return приближённая площадь графика функции, которая вызывает этот метод, на интервале [lowerLimit; upperLimit]
     */
    public double gaussMethod(double step, double lowerLimit, double upperLimit) {
        double result = 0;
        for (double i = lowerLimit; i < upperLimit; i += step) {
            result += gaussThreeNodes(i, i + step);
        }

        return result;
    }




    /**
     * Уточнённое значение интеграла. Уточнено путём поправки его значения на каждом численном интервале на величину,
     * полученную по правилу Рунге
     * @param step - шаг
     * @param lowerLimit - нижняя граница
     * @param upperLimit - верхняя граница
     * @return уточнённое значение интеграла
     */
    public double refinedTrapezoid(double step, double lowerLimit, double upperLimit) {
        double result = 0;
        for (double i = lowerLimit; i < upperLimit; i += step) {
            result += trapezoid(i, i + step) + rungeRuleTrapezoid(i, i + step);
        }

        return result;
    }




    /**
     * Площадь трапеции с основаниями: значение функции в левой границе, значение функции в правой границе и высотой:
     * правая гранциа минус левая граница, где под функцией понимается функция, которая вызвала данный метод и чью
     * приближённую площадь мы хотим найти
     * @param lowerLimit - левая граница
     * @param upperLimit - правая граница
     * @return площадь трапеции на отрезке [lowerLimit; upperLimit]
     */
    private double trapezoid(double lowerLimit, double upperLimit) {
        if (this == SIN) {
            return (this.getValue(lowerLimit * Math.PI) + this.getValue(upperLimit * Math.PI)) *
                   (upperLimit - lowerLimit) / 2;
        }
        return ((this.getValue(lowerLimit) + this.getValue(upperLimit)) * (upperLimit - lowerLimit)) / 2;
    }

    /**
     * Площадь графика многочлена второй степени, приближающего функцию, которая вызвала данный метод
     * @param lowerLimit - левая граница
     * @param upperLimit - правая граница
     * @return площадь графика многочлена второй степени на отрезке [lowerLimit; upperLimit]
     */
    private double quadraticPolynomial(double lowerLimit, double upperLimit) {
        if (this == SIN) {
            return ((upperLimit - lowerLimit) / 6) * (this.getValue(lowerLimit * Math.PI) +
                                                      4 * this.getValue((lowerLimit + upperLimit) * Math.PI / 2) +
                                                      this.getValue(upperLimit * Math.PI));
        }
        return ((upperLimit - lowerLimit) / 6) * (this.getValue(lowerLimit) +
                                                  4 * this.getValue((lowerLimit + upperLimit) / 2) +
                                                  this.getValue(upperLimit));
    }

    /**
     * Приближённая площадь графика функции, которая вызвала данный метод, получаемая методом Гаусса
     * @param a - левая граница
     * @param b - правая граница
     * @return приближённая площадь функции на отрезке [a; b]
     */
    public double gaussThreeNodes(double a, double b) {
        double x1 = ((a + b) / 2) - ((b - a) / 2) * (Math.sqrt(3) / Math.sqrt(5));
        double x2 = (a + b) / 2;
        double x3 = ((a + b) / 2) + ((b - a) / 2) * (Math.sqrt(3) / Math.sqrt(5));
        if (this == SIN) {
            return (5 * this.getValue(x1 * Math.PI) +
                    8 * this.getValue(x2 * Math.PI) +
                    5 * this.getValue(x3 * Math.PI)) * (b - a) / 18;
        }
        return (5 * this.getValue(x1) +
                8 * this.getValue(x2) +
                5 * this.getValue(x3)) * (b - a) / 18;
    }




    /**
     * Приблизительная погрешность, полученная по правилу Рунге для метода трапеций
     * @param lowerLimit - нижняя граница
     * @param upperLimit - верхняя граница
     * @return приблизительную погрешность, полученную по правилу Рунге на отрезке [lowerLimit; upperLimit]
     */
    private double rungeRuleTrapezoid(double lowerLimit, double upperLimit) {
        double cutLength = upperLimit - lowerLimit;
        return (4. / 3) * (this.trapezoidMethod(cutLength / 2, lowerLimit, upperLimit) -
                           this.trapezoidMethod(cutLength, lowerLimit, upperLimit));
    }
}
