# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**.



- Activity présente => ListNeighbourActivity.java

- Tablayout avec 2 onglets (1er pour la liste des neighbours, 2ème pour la future liste des favorit)

- 1 seul fragment est créer (NeighbourFragment.java). Il est lancé à partir de l'adapter => ListNeighbourPageAdapter.java lui même lancé par l'activité existante ListNeighbourActivity.java


Création et ajout du 2ème fragment devant contenir la liste des neighbours mit en favorit :

- 2ème fragment créé => FavoriteFragment.java
- création et ajout du 1er et 2ème fragment dans un array dans l'activité existante et envoie dans l'adapter pour traitement et affichage des 2 fragments.
- Modification de la classe Neighbour.java pour ajouter les nouvelles propriétés d'un neighbour (téléphone, site internet, adresse, A propos de moi....)
- getter et setter des nouvelles propriétés d'un Neighbour.
- ajout en "dur" des nouvelles infos des neighbours dans la classe abstraite DummyNeighbourGenerator.java (c'est elle qui contient les infos des voisins)
- Ajout dans l'interface NeighbourApiService.java et la classe DummyNeighbourApiService.java de la prochaine méthode "makeFavoriteNeighbour".
- ajout d'une condition à la méthode initList() du fragment des favorits => on parse la liste des neighbours et on récupère pour afficher seulemnt les favorits (boolean true dans la propriété de la class Neighbour.java")

- création de la recyclerView du fragment des favoris :

		- layout xml => fragment_neighour_favorite.xml => vue du fragment (avatar, nom, btn favorit)
		- layout xml => fragment_favorite_neighbour_list.xml => vue de la liste des fragment dans la recyclerView
		- classe MyFavoriteNeighbourRecyclerViewAdapter.java => 
		- fonction pour retirer un neighbour dans la liste des favorits associé au click sur le btn de la view de l'item (étoile)


- On place un écouteur. fragment "FavoriteFragment.java" s'abonne à l'action de supprimer un voisin du fragment "NeighbourFragment.java" afin de réinitialiser la recyclerView des favorits => si un voisin se fait supprimer, on le supprime aussi de la liste des favorits. On utilise LocalBroadcastManager pour passer l'info.

- fonction pour lancer la nouvelle activité lors du click de la view de l'item des recyclerView des 2 fragments.
 on passe via "Bundle" pour transférer ID du voisin (l'identifiant unique du Neighbour) à la nouvelle activité => DetailNeighbourActivity.java

Affichage de la seconde activité "DetailNeighbourActivity"

-création du layout => activity_detail_neighbour.xml
- on récupère les infos du neighbour à partir de l'ID récupéré avec Bundle.
-assignation des info dans les textView du layout
- modification de l'affichage du numéro de téléphone pour qu'il corresponde à la maquette de l'application ("0629493243" s'affichera "+33 6 29 49 32 43")
- ajout de la fonction pour ajouter ou retire le neighbour de la liste des favorit lors du click sur le btn favorit (étoile)
- ajout de la méthode pour revenir en arrière. 

Fermeture de l'activité avec "finish()" lors du click sur le bouton retour (fleche)
Le "onResume()" de la première activité s'active (lorsque l'activité redevient visible et passe au premier plan) et réinitialise les fragments pour mettre les listes des fragmenst à jours.
