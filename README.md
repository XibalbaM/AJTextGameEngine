# Structure :
- `.gihtub/workflws/CD.yml` : L'action github qui permet de déployer l'application sur github pages. Elle compile le site avec kobweb et le déploie sur github pages.
- `.kobweb/conf.yaml` : Le fichier de configuration de kobweb. Il définit le titre du site, le chemin sur lequel il est déployé (car avec github pages, j'ai un préfixe d'url) le port de développement et l'emplacement des fichiers.
- `buildSrc` : Du code kotlin qui est accessible dans le `build.gradle.kts`. Il permet donc de faire des taches de build plus complexes.
- `gradle/wrapper`, `gradlew`, `gradlew.bat` : Les exécutables de gradle, inclus sur github pour s'assurer que la version de gradle est la même partout.
- `schemas` : Les schémas json utilisés pour la validation des fichiers yaml des histoires.
- `src` : Le code du site.
- `stories` : Les fichiers yaml des histoires. Ils sont validés par les schémas json et transformés en code kotlin par le code dans `buildSrc`.
- `build.gradle.kts` : Configure le build gradle. Définit les plugins, les dépendances, les tâches et les cibles de compilation.
- `settings.gradle.kts` : Configure le projet gradle. Il définit les sources pour les plugins, le nom du projet et éventuellement les sous-projets.

La plupart des fichiers sont commentés.