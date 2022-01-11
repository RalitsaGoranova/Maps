package Ver;

import java.util.*;

public class HeroesOfCode7 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=Integer.parseInt(scanner.nextLine());
        Map<String , List<Integer>>heroes=new TreeMap<>();
        for(int i=1;i<=n;i++)
        {
            String[] in=scanner.nextLine().split("\\s");
            List<Integer> nz=new ArrayList<>();
            String name=in[0];
            int hp=Integer.parseInt(in[1]);
            int mp=Integer.parseInt(in[2]);
            if(hp>100||mp>200)
            {
                return;
            }
            nz.add(hp);
            nz.add(mp);
            if(!heroes.containsKey(name)) {
                heroes.put(name, nz);
            }
        }
        String com=scanner.nextLine();
        while (!com.equals("End"))
        {
            String[] input=com.split("\\s\\-\\s");
            String command=input[0];
            String name=input[1];
           switch (command)
           {
               case "CastSpell":
                   int mpneed=Integer.parseInt(input[2]);
                   String spname=input[3];
                   int razlika=heroes.get(name).get(1)-mpneed;
                   if(razlika>=0)
                   {
                     int hp=heroes.get(name).get(0);
                     int mp=heroes.get(name).get(1)-mpneed;
                     List<Integer> nz=new ArrayList<>();
                     nz.add(hp);
                     nz.add(mp);
                     heroes.put(name , nz);
                     System.out.println(name+	" has successfully cast "+spname+" and now has "+razlika+" MP!");
                   }else System.out.println(name+" does not have enough MP to cast "+spname+"!");
           break;
               case "TakeDamage":int damage=Integer.parseInt(input[2]);
               String attacker=input[3];
                   int razlika1=heroes.get(name).get(0)-damage;
                  if(razlika1>0)
                  {
                      int mp=heroes.get(name).get(1);
                      List<Integer> nz=new ArrayList<>();
                      nz.add(razlika1);
                      nz.add(mp);
                      heroes.put(name,nz);
                      System.out.println(name+	" was hit for "+damage+" HP by "+attacker+" and now has "+razlika1+" HP left!");
                  }else {System.out.println(name+" has been killed by "+attacker+"!");
                  heroes.remove(name);}
          break;
               case "Recharge":
                   int amount=Integer.parseInt(input[2]);
                   int mp=heroes.get(name).get(1)+amount;
                   if(mp>200)
                   {
                       int krai=200-heroes.get(name).get(1);
                       mp=200;
                       System.out.println(name+	" recharged for "+krai+" MP!");
                   }else System.out.println(name+	" recharged for "+amount+" MP!");
                   int hp=heroes.get(name).get(0);
                   List<Integer> nz=new ArrayList<>();
                   nz.add(hp);
                   nz.add(mp);
                   heroes.put(name, nz);
                   break;
               case "Heal":
                   int amount1=Integer.parseInt(input[2]);
                   int hp1=heroes.get(name).get(0)+amount1;
                   if(hp1>100)
                   {
                      int krai1=100-heroes.get(name).get(0);
                       hp1=100;
                       System.out.println(name+	" healed for "+krai1+" HP!");
                   }else System.out.println(name+	" healed for "+amount1+" HP!");
                   int mp1=heroes.get(name).get(1);
                   List<Integer> nz1=new ArrayList<>();
                   nz1.add(hp1);
                   nz1.add(mp1);
                   heroes.put(name, nz1);
                   break;

           }

            com=scanner.nextLine();
        }
        heroes.entrySet().stream().sorted((e1,e2)->e2.getValue().get(0).compareTo(e1.getValue().get(0))).forEach(e->System.out.printf("%s%n  HP: %d%n  MP: %d%n", e.getKey(),e.getValue().get(0),e.getValue().get(1)));
    }
}
