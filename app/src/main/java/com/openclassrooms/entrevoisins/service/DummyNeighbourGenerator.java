package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Alors dites vous que c’est un combat réel et montrez-moi ce que vous avez dans l’slibard! On dit que là où il passe l’herbe ne repousse pas! Un chef de guerre qui commande plus c’est pas bon.", "35 rue du champignon", "0629493243", "www.caroline.com", false),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "C’est du patrimoine ça? Très exactement c’est «Provençal le Gaulois». Moi je passe pas mal de temps à la taverne. J’ai jamais entendu parlé de celui-là!", "25 rue du Curly", "0629493243", "www.jack.com", true),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "Il va déprimer, il va s’mettre à bouffer, il va prendre quarante livres! Mais oui mon p’tit père il faudra bien vous y coller! À moins que vous préfériez vous taper les tartes!", "33 rue du pomme", "0629493243", "www.chloe.com", true),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "C’est meilleur chaud, hein. Seulement là aux cuisines ils sont sur le repas du soir. Hein, ils ont pas trop le temps. S’il vous plait! Faites pas votre mijoré! ", "1 rue du banane", "0629493243", "www.vincent.com", false),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "Je suis chef de guerre moi, je suis pas là pour secouer des drapeaux et jouer de la trompette. S'il y a un autre qui groupe qui arrive par là on est marron des deux côtés.", "22 rue du tagada", "0629493243", "www.elodie.com", true),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "Moi je me fais traiter de gonzesse j’en fais pas tout un cake! Mais vous êtes complètement con? Moi je passe pas mal de temps à la taverne. J’ai jamais entendu parlé de celui-là! ", "12 rue du pépito", "0629493243", "www.sylvain.com", false),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", " Et on peut savoir depuis quand vous arpentez la Bretagne en racontant à tout le monde que vous vous appelez Provençal le Gaulois? ", "13 rue du chocapic", "0629493243", "www.laetitia.com", false),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "Allez en garde ma mignonne! Non, j’vais varier les fruits, n’vous inquiétez pas. Là c’est une table ronde. Pour que les chevaliers de Bretagne se réunissent autour.", "19 rue du prince de Lu", "0629493243", "www.dan.com", false),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Toute façon autant vous y faire parce qu’à partir de maintenant on va s’appeler «Les Chevaliers de la Table Ronde». Alors là, personne sait qui est tombé et tout le monde s’en fout! ", "8 rue du malabar", "0629493243", "www.joseph.com", false),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "A propos de moi", "5 rue du cookie", "0629493243", "www.emma.com", false),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "A propos de moi", "4 rue du chamallow", "0629493243", "www.patrick.com", false),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "A propos de moi", "3 rue du mamie nova", "0629493243", "www.ludovic.com", false)
    );

    //--------------------AJOUT--------------------------------------------------------------------
    public static List<Neighbour> FAKE_DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(13, "Lea", "https://api.adorable.io/AVATARS/512/21.png", "Toute façon autant vous y faire parce qu’à partir de maintenant on va s’appeler «Les Chevaliers de la Table Ronde». Alors là, personne sait qui est tombé et tout le monde s’en fout! ", "8 rue du malabar", "0629493243", "www.joseph.com", false),
            new Neighbour(14, "Geoffrey", "https://api.adorable.io/AVATARS/512/22.png", "Toute façon autant vous y faire parce qu’à partir de maintenant on va s’appeler «Les Chevaliers de la Table Ronde». Alors là, personne sait qui est tombé et tout le monde s’en fout! ", "8 rue du malabar", "0629493243", "www.joseph.com", false),
            new Neighbour(15, "Simon", "https://api.adorable.io/AVATARS/512/23.png", "Toute façon autant vous y faire parce qu’à partir de maintenant on va s’appeler «Les Chevaliers de la Table Ronde». Alors là, personne sait qui est tombé et tout le monde s’en fout! ", "8 rue du malabar", "0629493243", "www.joseph.com", false),
            new Neighbour(16, "André", "https://api.adorable.io/AVATARS/512/24.png", "Toute façon autant vous y faire parce qu’à partir de maintenant on va s’appeler «Les Chevaliers de la Table Ronde». Alors là, personne sait qui est tombé et tout le monde s’en fout! ", "8 rue du malabar", "0629493243", "www.joseph.com", false),
            new Neighbour(17, "Leopold", "https://api.adorable.io/AVATARS/512/25.png", "Toute façon autant vous y faire parce qu’à partir de maintenant on va s’appeler «Les Chevaliers de la Table Ronde». Alors là, personne sait qui est tombé et tout le monde s’en fout! ", "8 rue du malabar", "0629493243", "www.joseph.com", false)
    );
    //----------------------------------------------------------------------------------------

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
