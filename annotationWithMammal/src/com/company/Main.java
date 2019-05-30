package com.company;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;

public class Main {

    public static void main(String[] args) {
        Giraffe g = new Giraffe();
        Rhino r = new Rhino();
        Elephant e = new Elephant();
        Cat c = new Cat();
        Hippopotamus h = new Hippopotamus();

        Animal[] animals = {g, r, e, c, h};

        for (Animal item : animals) {

            Class gc = item.getClass();
            Annotation[] annotations = gc.getAnnotations();
            System.out.println(gc.getSimpleName());

            for (Annotation an : annotations) {
                System.out.println("\t" + an.annotationType().getSimpleName());
                if (an instanceof Mammal) {
                    Mammal m = (Mammal) (an);
                    System.out.println("\t\t" + m.color());
                    System.out.println("\t\t" + m.sound());
                }
                if (an instanceof Mammals)
                {
                    Mammals m = (Mammals)an;
                    Mammal[] arr = ((Mammals) an).value();
                    for(Mammal m2 : arr)
                    {
                        System.out.println("\t\t" + m2.color());
                        System.out.println("\t\t" + m2.sound());
                    }
                }
            }
        }
    }
}

@Inherited // Наследование
@Repeatable(Mammals.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Mammal {
    String sound();

    int color() default 0xffffff;
}

@Inherited // Наследование
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Mammals{
    Mammal[] value();
}

@Mammal(sound = "auuuuuuf")
abstract class Animal {

}

class Giraffe extends Animal {

}

@Mammal(color = 0x8ca2ab, sound = "hhhhmmm")
class Rhino extends Animal {

}

class Elephant extends Animal {

}

@Mammal(color = 0x000000, sound = "m`euw")
@Mammal(color = 0x000011, sound = "gav")
class Cat extends Animal {

}

class Hippopotamus extends Animal {

}
