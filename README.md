# TP Multiservices

Ce dépôt permet de **s'entraider et de mettre en commun nos solutions** dans le cadre du **TP Multiservices**.

## Comment contribuer ?

La branche principale (`main`) est protégée et ne doit pas être modifiée directement.

Pour contribuer au projet, vous devez :

1. Forker ce dépôt sur votre compte GitHub
2. Cloner votre fork
3. Créer une branche à votre nom
4. Effectuer vos modifications
5. Envoyer votre branche sur votre fork
6. Créer une Pull Request vers ce dépôt

---

### 1. Forker le dépôt

Sur la page GitHub de ce dépôt, cliquez sur le bouton **Fork** en haut à droite.

GitHub va créer une copie du dépôt sur votre propre compte.

Vous pourrez ainsi travailler librement sur votre copie sans modifier directement le dépôt principal.

### 2. Cloner votre fork

Une fois le fork créé, clonez **votre fork** et non le dépôt original :

```bash
git clone <URL_DE_VOTRE_FORK>
cd <NOM_DU_PROJET>
```

Vous pouvez récupérer l'URL depuis le bouton **Code** de votre fork.

### 3. Créer une branche à votre nom

Créez une nouvelle branche à partir de `main` :

```bash
git checkout -b votre-nom
```

Par exemple :

```bash
git checkout -b pierre
```

Vous travaillerez ensuite uniquement sur cette branche.

### 4. Effectuer vos modifications

Une fois vos modifications terminées :

```bash
git add .
git commit -m "Description des modifications"
```

Vous pouvez évidemment effectuer plusieurs commits au cours de votre travail.

### 5. Envoyer votre branche sur votre fork

Envoyez ensuite votre branche sur GitHub :

```bash
git push -u origin votre-nom
```

Par exemple :

```bash
git push -u origin pierre
```

Votre travail est maintenant disponible sur votre fork GitHub.

### 6. Créer une Pull Request

Lorsque votre solution est prête, rendez-vous sur GitHub.

Depuis votre fork, cliquez sur **Contribute**, puis sur **Open pull request**.

Vérifiez que la Pull Request est configurée comme ceci :

```text
base repository : dépôt original
base            : main

←

head repository : votre fork
compare         : votre-nom
```

Vous pouvez ensuite créer la Pull Request en décrivant rapidement les modifications apportées.

## Mise en commun

Les différentes solutions proposées pourront être comparées grâce aux **Pull Requests**.

La solution retenue pourra ensuite être fusionnée dans la branche principale (`main`).

> **Important :** ne travaillez pas directement sur `main`. Créez une branche sur votre fork et proposez vos modifications avec une Pull Request.
