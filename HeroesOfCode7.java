package Ver;

import java.util.*;

public class HeroesOfCode7 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=Integer.parseInt(scanner.nextLine());
        Map<String , List<Integer>>heroes=new TreeMap<>();
        for(int i=1;i<=n;i++)
        {
            String[] input=scanner.nextLine().split("\\s");
            List<Integer> data=new ArrayList<>();
            String name=input[0];
            int hp=Integer.parseInt(input[1]);
            int mp=Integer.parseInt(input[2]);
            if(hp>100||mp>200)
            {
                return;
            }
            data.add(hp);
            data.add(mp);
            if(!heroes.containsKey(name))
            {
                heroes.put(name, data);
            }
        }

        String commandEnd=scanner.nextLine();
        while (!commandEnd.equals("End"))
        {
            String[] input=commandEnd.split("\\s\\-\\s");
            String command=input[0];
            String name=input[1];
            switch (command) {
                case "CastSpell" -> {
                    int mpneed = Integer.parseInt(input[2]);
                    String spname = input[3];
                    int subtraction = heroes.get(name).get(1) - mpneed;
                    if (subtraction >= 0)
                    {
                        int hp = heroes.get(name).get(0);
                        int mp = heroes.get(name).get(1) - mpneed;
                        List<Integer> nz = new ArrayList<>();
                        nz.add(hp);
                        nz.add(mp);
                        heroes.put(name, nz);
                        System.out.println(name + " has successfully cast " + spname + " and now has " + subtraction + " MP!");
                    }
                    else
                    {
                        System.out.println(name + " does not have enough MP to cast " + spname + "!");
                    }
                }
                case "TakeDamage" -> {
                    int damage = Integer.parseInt(input[2]);
                    String attacker = input[3];
                    int subtraction = heroes.get(name).get(0) - damage;
                    if (subtraction > 0)
                    {
                        int mp = heroes.get(name).get(1);
                        List<Integer> data = new ArrayList<>();
                        data.add(subtraction);
                        data.add(mp);
                        heroes.put(name, data);
                        System.out.println(name + " was hit for " + damage + " HP by " + attacker + " and now has " + subtraction + " HP left!");
                    }
                    else
                    {
                        System.out.println(name + " has been killed by " + attacker + "!");
                        heroes.remove(name);
                    }
                }
                case "Recharge" -> {
                    int amount = Integer.parseInt(input[2]);
                    int mp = heroes.get(name).get(1) + amount;
                    if (mp > 200)
                    {
                        int krai = 200 - heroes.get(name).get(1);
                        mp = 200;
                        System.out.println(name + " recharged for " + krai + " MP!");
                    }
                    else System.out.println(name + " recharged for " + amount + " MP!");

                    int hp = heroes.get(name).get(0);
                    List<Integer> data = new ArrayList<>();
                    data.add(hp);
                    data.add(mp);
                    heroes.put(name, data);
                }
                case "Heal" -> {
                    int amount = Integer.parseInt(input[2]);
                    int hp = heroes.get(name).get(0) + amount;
                    if (hp > 100)
                    {
                        int end = 100 - heroes.get(name).get(0);
                        hp = 100;
                        System.out.println(name + " healed for " + end + " HP!");
                    }
                    else System.out.println(name + " healed for " + amount + " HP!");
                    int mp = heroes.get(name).get(1);
                    List<Integer> data = new ArrayList<>();
                    data.add(hp);
                    data.add(mp);
                    heroes.put(name, data);
                }
            }
            commandEnd=scanner.nextLine();
        }
        heroes.entrySet().stream().sorted((e1,e2)->e2.getValue().get(0)
                .compareTo(e1.getValue().get(0)))
                .forEach(e->System.out.printf("%s%n  HP: %d%n  MP: %d%n", e.getKey(),e.getValue().get(0),e.getValue().get(1)));
    }
}
