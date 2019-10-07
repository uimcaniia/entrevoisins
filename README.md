

##Identification des parties prenantes 

####Commanditaire : ClaireParlo, Entrevoisins
####É​quipeprojet : Développeur
####Autre partie prenante : Charline, UX Designer freelance

----

Les fonctionnalités déjà présente dans  [l'application Entrevoisins](https://github.com/Deyine/OpenClassrooms/tree/master/Android/Entrevoisins)

>- lister mes voisins;
- suppression d'un voisin.
- la fonctionnalité "Ajouter un voisin" n'existe pas contrairement à ce que présente la note de cadrage !

version SDK : API 28
version Java : 1.8

L’API minimale définit la version minimale de l’API d’Android avec laquelle l'application doit être compatible => version plugin android Gradle : 3.5.0
L’API cible est l’API avec laquelle on va compiler l’application => version Gradle : 5.4.1

----

###Pour compiler et exécuter l'application : 

*Pour compiler, Android va se servir de l'outil nommé Gradle qui permet d’effectuer toutes les tâches complexes nécessaires à la compilation.*

- Pour Exécuter, dans la barre d'outils, sélectionnez l'application ("app") dans le menu déroulant des configurations d'exécution.

- Dans le menu déroulant du périphérique cible, sélectionnez le périphérique sur lequel vous souhaitez exécuter votre application ("émulateur, téléphone.."). Si c'est un téléphone réel, vous devez activer l’option “Débuggage USB” qui se trouve dans le menu caché “Options pour les développeurs”. Pour activer ce menu caché :

    >- Rendez-vous dans les options du téléphone ;
    - Cliquez sur “A propos du téléphone” ;
    - Cliquez 7 fois sur “Numéro de build”.

- Cliquer sur le bouton "Exécuter" (Run) en forme de flèche de lecture. L'émulateur ou le téléphone afficher l'application.

----

### Activity présente : ListNeighbourActivity.java

> Tablayout avec 2 onglets (1er pour la liste des neighbours, 2ème pour la future liste des favorit)

>1 seul fragment est créer (NeighbourFragment.java). Il est lancé à partir de l'adapter => ListNeighbourPageAdapter.java lui même lancé par l'activité existante ListNeighbourActivity.java


----
##AJOUT A PARTIR DE L'EXISTANT


###Création et ajout du 2ème fragment devant contenir la liste des neighbours mit en favorit :


- 2ème fragment créé => FavoriteFragment.java
- Ajout du 1er et 2ème fragment dans un array dans l'activité existante et envoie le l'array dans l'adapter (ListNeighbourPagerAdapter) pour traitement, initialisation et affichage des 2 fragments.
- Modification de la classe Neighbour.java pour ajouter les nouvelles propriétés d'un neighbour (téléphone, site internet, adresse, A propos de moi....)
- getter et setter des nouvelles propriétés d'un Neighbour.
- ajout en "dur" des nouvelles infos des neighbours dans la classe abstraite DummyNeighbourGenerator.java (c'est elle qui contient les infos des voisins)
- Ajout dans l'interface NeighbourApiService.java et la classe DummyNeighbourApiService.java de la prochaine méthode "makeFavoriteNeighbour".
- ajout d'une condition à la méthode initList() du fragment des favorits => on parse la liste des neighbours et on récupère pour afficher seulement les favorits (boolean true dans la propriété de la class Neighbour.java")

- création de la recyclerView du fragment des favoris :

> layout xml => fragment_neighour_favorite.xml => vue du fragment (avatar, nom, btn favorit)

>layout xml => fragment_favorite_neighbour_list.xml => vue de la liste des fragments dans la recyclerView

>classe MyFavoriteNeighbourRecyclerViewAdapter.java
 
>fonction pour retirer un neighbour dans la liste des favorits associé au click sur le btn de la view de l'item (étoile)


- On place un écouteur sur le bouton delete des items de la RecyclerView de NeighbourFragment.java.
 	=>Le fragment "FavoriteFragment.java" s'abonne à cette action de supprimer un voisin du fragment "NeighbourFragment.java" afin de réinitialiser sa propre recyclerView des favorits 
	=> si un voisin se fait supprimer, on le supprime aussi de la liste des favorits.
	=> On utilise LocalBroadcastManager pour passer l'info.

- fonction pour lancer la nouvelle activité lors du click sur l'ensemble de la view de l'item des recyclerView des 2 fragments.
	=>on passe via "Bundle" pour transférer ID du voisin (l'identifiant unique du Neighbour) à la nouvelle activité => DetailNeighbourActivity.java

----

###Affichage de la seconde activité : DetailNeighbourActivity

- création du layout => activity_detail_neighbour.xml

*on récupère les infos du neighbour à partir de l'ID récupéré avec Bundle.*

- assignation des info dans les textView du layout
- modification de l'affichage du numéro de téléphone pour qu'il corresponde à la maquette de l'application ("0629493243" s'affichera "+33 6 29 49 32 43")
- ajout de la fonction pour ajouter ou retire le neighbour de la liste des favorits lors du click sur le btn favorit (étoile)
- ajout de la méthode pour revenir en arrière. 

Fermeture de l'activité avec "finish()" lors du click sur le bouton retour (fleche)

*Le "onResume()" de la première activité s'active (lorsque l'activité redevient visible et repasse au premier plan) et réinitialise les fragments pour mettre les listes des fragments à jour.*

- ajout de la fonctionnalité "Ajouter un voisin"
		- Bouton flottant pour ajouter un voisin aléatoirement
		- Méthode implémenté pour l'ajout


----
##TEST  + ANDROID TEST


##unitaire : 

###NeighbourServiceTest (fichier existant pour la 1ère activité)

####Tests existants : 
			
>getNeighboursWithSuccess() => On vérifie que l'on récupère une liste de voisin avec la méthode getNeighbours()


>deleteNeighbourWithSuccess() => On vérifie qu'un voisin se fait correctement supprimer avec deleteNeighbour(neighbourToDelete)

----
###tests ajoutés : 

    addNewNeighbourWithSuccess() => Vérifie qu'un voisin se fait correctement ajouter à la liste actuel de voisin.

    changeNeighbourToFavoriteWithSuccess() => vérifie que le status de "favoris" d'un voisin se fait correctement modifier par la méthode makeFavoriteNeighbour(neighbourToFavorite, true). On passe le status de "non favoris" en "favoris".

    changeNeighbourToNotFavoriteWithSuccess() => Sens inverse du test précédent. passe du status "favoris" en "non favoris".

    getFavoriteNeighboursWithSuccess() => Vérifie qu'avec la méthode getFavoriteNeighbour(), on récupère bien que les neighbours favoris.

----
###NeighbourDetailTest 
(nouveau fichier pour les test de la seconde activité. Il est préférable de séparer les test des activités différentes)

    transformedNumberPhone_InGoodFormat() => vérifie qu'avec getPhoneNumberFormat(), le format du téléphone se fait correctement : "0629493243" deviendra "+33 6 29 49 32 43"    
    
    getNeighbourSelected_WithGoodId_WithSuccess() => vérifie qu'avec la méthode getNeighbourSelectedWithGoodId(), on récupère bien le bon voisin en comparant son ID avec celui de la liste de voisin originale.

    change_FavoriteStatusNeighbour_WithSuccess_IfInitialStatusIsNotTheSame()=> vérifie avec refreshFavoritNeighbour() que le status de favoris a bien été changé, si la valeur initial est différente de la nouvelle valeur

    NoChange_FavoriteStatusNeighbour_WithSuccess_IfStatusIsTheSame() => identique que le test au-dessus, sauf que on ne change pas son status de favorit si la valeur initial est identique de la nouvelle valeur

----
##Android Test : 

###NeighboursListTest 
(fichier existant pour la 1ère activité).

###Tests existants :

>myNeighboursList_shouldNotBeEmpty() => On vérifie que la liste de voisin n'st pas vide

>myNeighboursList_deleteAction_shouldRemoveItem() => On vérifie qu'un voisin se fait correctement supprimer de la liste

----
###tests ajoutés : 
	onFavoriteNeighbourView_showOnlyFavoredNeighbour() => on vérifie que lors du click sur l'onglet "favoris" on affiche bien les voisins favoris

	onMyNeighboursList_deleteActionOnFavoriteNeighbourItem_shouldRemoveItemInMyFavoriteNeighbourList() => on vérifie que lors de la suppresion d'un voisin, il se fait également suppimer de la liste des favoris (logique)

	onMyFavoriteNeighbourList_deleteFavoriteStatusAction_shouldRemoveItemInMyFavoriteNeighbourList() =>  dans l'oinglet de la liste des favoris, si on click sur le bouton en forme d'étoile, cela supprime bien le status de "favoris" du voisin et cela supprime le voisin de la liste en même temps.

	onNeighbourList_addNewNeighbourAction_shouldaddItemInNeighbourList() => on vérifie qu'un nouveau voisin se fait correctement ajouté à la liste des voisin lorsqu'on click sur le bouton ajouté en forme de "+".

----
###DetailNeighbourActivityTest 
(nouveau fichier pour les test de la seconde activité)

    onDetailNeighbourWindow_nameOfNeigbourSelectInTextView_mustBeCorrect() => On vérifie que la seconde activité affiche le détail du bon voisin sélectionné dans la liste de la précédente activité en comparant les prénoms.

	onMyNeighboursList_WhenNeighbourIsClick_ShowDetailNeighbourWindow() => vérifie si l'activité 2 se lance bien après avoir cliqué sur un voisin en vérifiant si le TextView "A propos de moi" est affiché.

	onViewDetail_comeBackAction_showfirstActivity() => vérifie que lors du click sur le bouton de retour de la seconde activité, on retourne bien sur la première.

	onViewDetail_addFavoriteStatusAction_shouldAddesItemInMyFavoriteNeighbourList() => vérifie si, un voisin qui n'est pas dans la liste des favoris, va bien avoir le status de favoris de changé et son ajout à la liste des favoris, si on click sur le bouton en forme d'étoile dans la deuxième activité qui affiche les détails de ce voisin.

	onViewDetail_deleteFavoriteStatusAction_shouldRemoveItemInMyFavoriteNeighbourList() => idem que le test au dessus, mais vérifie si il se fait retiré correctement de la liste.
