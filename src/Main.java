import enums.HeroName;
import enums.HeroRole;
import exceptions.CantBeginMatchException;
import model.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Set<Hero> heroes = new HashSet<>();
        Hero pudge = new Hero(HeroName.PUDGE.name(), HeroRole.MIDER.name(), 1250, 500, 220);
        Hero puck = new Hero(HeroName.PUCK.name(), HeroRole.MIDER.name(), 1250, 500, 220);
        Hero trentProtector = new Hero(HeroName.TRENT_PROTECTOR.name(), HeroRole.FULL_SUPPORT.name(), 30, 15, 20);
        heroes.add(pudge);
        heroes.add(puck);
        heroes.add(trentProtector);
        heroes.add(new Hero(HeroName.AXE.name(), HeroRole.HARDLINER.name(), 25, 17, 16));
        heroes.add(new Hero(HeroName.UNDYING.name(), HeroRole.HARDLINER.name(), 24, 12, 10));
        heroes.add(new Hero(HeroName.PHANTOM_ASSASSIN.name(), HeroRole.CARRY.name(), 18, 25, 14));

        Set<Item> items = new HashSet<>();
        items.add(new Item("Desolator", 3500));
        items.add(new Item("Daedalus", 5400));
        items.add(new Item("Divine Rapier", 7600));
        items.add(new Item("Black King Bar", 4500));
        items.add(new Item("Tango", 50));
        items.add(new Item("Mango", 75));
        items.add(new Item("Batterfly", 5450));

        Player player1 = new Player("Danylo", 30, "Dendy", "Ukrainian", HeroRole.MIDER.name(), pudge);
        player1.setFreeTimeInHoursPerWeek(20);
        Player player2 = new Player("Sasha", 18, "Mortred", "Ukrainian", HeroRole.FULL_SUPPORT.name(), trentProtector);
        player2.setFreeTimeInHoursPerWeek(30);
        Player player3 = new Player("Alexandr", 20, "TORONTOTOKYO", "Russian", HeroRole.MIDER.name(), puck);

        Person myPerson = new Player("Miracle", 22, "Miracle", "ff", HeroRole.MIDER.name(), puck);
        myPerson.sayName();

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Trainer trainer = new Trainer("Andriy", 25, 7);

        Team team1 = new Team("NAVI", "Ukrainian", players, trainer);
        Team team2 = new Team("Virtus pro", "Russian", players, trainer);

        team1.setPlayers(players);
        Match match = new Match(team1, team2);
        try {
            match.beginMatch();
        } catch (CantBeginMatchException e) {
            e.printStackTrace();
        }

        trainer.setTeam(team1);
        trainer.trainingApproach();

        player1.gankRequest(player2);
        player1.setRegistered(true);
        player2.setRegistered(false);
        System.out.println(team1.checkTeamRegistration());

        player1.setMmr(8500);
        player2.setMmr(7400);
        team1.wonGame();
        System.out.println(player1.getMmr());
        System.out.println(player2.getMmr());

        player1.setFreeTimeInHoursPerWeek(20);
        player1.training();
        System.out.println(player1.getFreeTimeInHoursPerWeek());

        player1.setFreeTimeInHoursPerWeek(20);
        player2.setFreeTimeInHoursPerWeek(30);
        System.out.println(team1.teamAverageTimeInHoursPerWeeek());

        Game game = new Game(heroes, items);
        game.getMatches().add(match);

        for (Hero nextHero : heroes) {
            if (nextHero.getRole().toLowerCase(Locale.ROOT).equals("mider")) {
                System.out.println(nextHero.getName());
            }
        }

        Iterator<Hero> iterator = heroes.iterator();
        while (iterator.hasNext()){
            Hero hero = iterator.next();
            if (hero.getName().toLowerCase(Locale.ROOT).equals("axe")){
                System.out.println(hero.getName() + " was found");
            }
        }

        for (Player nextPlayer : players) {
            if (nextPlayer.registrationForMatch()) {
                if ((nextPlayer.getRole().equals(HeroRole.MIDER.name())) && (nextPlayer.getRole().equals(nextPlayer.getHero().getRole())))
                System.out.println("We've found mider of today's match: " + nextPlayer.getNickname());
            }
        }

        for (Item nextItem: items) {
            if (nextItem.getName().equals("Batterfly")){
                player1.getHero().setAgility(player1.getHero().getAgility() + 35);
                System.out.println(player1.getHero().getAgility());
            }
        }

        Set<Player> miders = new TreeSet<>();
        Set<Player> fullSupports = new TreeSet<>();

        Map<Player, String> playersRoles = new HashMap();
        playersRoles.put(player1, HeroRole.SEMI_SUPPORT.name());
        playersRoles.put(player2, HeroRole.FULL_SUPPORT.name());
        playersRoles.put(player3, HeroRole.MIDER.name());
        player1.setMmr(8500);
        player2.setMmr(6300);
        player3.setMmr(8500);


        Set<Map.Entry<Player, String>> entrySet = playersRoles.entrySet();
        for (Map.Entry<Player, String> entry : entrySet){
            if (entry.getKey().getRole().equals(HeroRole.MIDER.name())) {
                miders.add(entry.getKey());
            }
            if (entry.getKey().getRole().equals(HeroRole.FULL_SUPPORT.name())) {
                fullSupports.add(entry.getKey());
            }
        }

        for (Player nextPlayer : miders) {
            System.out.println(nextPlayer.getNickname());
        }

        for (Player nextPlayer : fullSupports) {
            System.out.println(nextPlayer.getNickname());
        }

        File file = new File("D:/KindGeek Java Projects/Homework/heroes_information.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        for (Hero nextHero : heroes) {
            fileWriter.append("Hero: " + nextHero.getName()).append(", role: " + nextHero.getRole() + "\n");
        }
        fileWriter.close();

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String i;
        while ((i = bufferedReader.readLine()) != null){
            System.out.println(i);
        }
        fileReader.close();

        File file1 = new File("D:/KindGeek Java Projects/Homework/src/working_with_files/items_information.txt");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        FileWriter fileWriter1 = new FileWriter(file1, false);
        for (Item nextItem : items) {
            fileWriter1.append("Item name: " + nextItem.getName() +
                    ", price: " + nextItem.getPrice() + "\n");
        }
        fileWriter1.close();

        Class<Player> cl = Player.class;
        System.out.println(cl.getName());
        System.out.println(cl.getSuperclass().getName());

        System.out.println(Arrays.toString(cl.getInterfaces()));

        for (Constructor<?> constructor : cl.getConstructors()) {
            System.out.println(Arrays.toString(constructor.getParameters()));
        }

        Field field = cl.getDeclaredField("nickname");
        field.setAccessible(true);
        System.out.println(field.get(player1));
        field.set(player1, "Petro");

        for (Field nextField : cl.getDeclaredFields()) {
            nextField.setAccessible(true);
            System.out.println(nextField.get(player1));
        }

        for (Method method : cl.getMethods()) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
//            System.out.println(method.getModifiers());
            System.out.println(Arrays.toString(method.getParameters()));
            System.out.println(method.getParameterCount());
            for (Parameter parameter : method.getParameters()) {
                System.out.println(parameter.getType() + " " + parameter.getName());
            }
        }

        Method method = cl.getDeclaredMethod("reflectionCheck");
        method.setAccessible(true);
        method.invoke(player1);

        Class[] params =  {String.class, int.class, String.class};
        Player player10 = cl.getConstructor(params).newInstance("Andriy", 25, "Collapse");
        System.out.println(player10);

        Field field1 = cl.getSuperclass().getDeclaredField("name");
        field1.setAccessible(true);
        System.out.println(field1.get(player10));

        Pick pick = new Pick(team1);
        System.out.println(pick.getPick().toString());

        for (Player nextPlayer :
                team1.getPlayers()) {
            System.out.println(nextPlayer.getMmr());
        }
        team1.wonGame();
        for (Player nextPlayer :
                team1.getPlayers()) {
            System.out.println(nextPlayer.getMmr());
        }

        game.addPeople(player1);
        game.addPeople(player2);
        game.addPeople(player3);
        game.addPeople(trainer);
        game.addPeople(() -> System.out.println("The journalist is taking interview"));
        game.addPeople(() -> System.out.println("The commentator is commenting match"));
        game.beginMatch();

        trainer.setSalary(5000);
        Thread playerThread = new Thread(player1);
        Thread trainerThread = new Thread(trainer);
        playerThread.start();
        trainerThread.start();
    }
}
