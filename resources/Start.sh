find Controleur Model Vue -name "*.java" -print | xargs javac -d bin

rsync -av profils bin/
rsync -av resources bin/

java -cp bin Vue.MenuAccueil