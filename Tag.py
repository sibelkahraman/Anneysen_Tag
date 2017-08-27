import numpy as np
from bs4 import BeautifulSoup
import re
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import string
from threading import Thread
from nltk.corpus import stopwords
from mysql.connector import(connection)
from time import sleep
text = []
X = []
Temp = []
nth = 1

c_similarity = []
copy_cs = []
np_array = []
cnx = connection.MySQLConnection(user='sibel', password='123456', host='localhost', database='sys')
cursor = cnx.cursor()
a = np.arange(6)
print(a[0].dtype)
def DB(val):
    start = (val - 1 ) * 1058
    end = val * 1058 + 1
    u = val - 1

    cursor.execute("""SELECT * FROM anne WHERE no > %s and no < %s""",(start,end))
    results = cursor.fetchall()
    for row in results:
        example1 = BeautifulSoup(row[3], 'html.parser')
        letters_only = re.sub("[^a-zA-ZğüşöçıİĞÜŞÖÇ]+ ^\d*[.,]?\d*$ +", " ", example1.getText())
        letters_only.encode('utf-8')
        a=  ' '.join(word.strip(string.punctuation) for word in letters_only.split())
        stops = set(stopwords.words("turkish"))
        k = ' '.join([word for word in a.split() if word not in stopwords.words("turkish")])
        lower_sent = k.lower()
        text[u].append(lower_sent)

    return
def vec_tr():
    for u in range(40):
        np.array(text[u], dtype=object)
        vec = TfidfVectorizer()
        vec.fit_transform(text[u])
        X.append([])
        print(u)
        X[u] = np.copy(text[u])
        np.array(X[u], dtype=object)

def similarity():
    for i in range(40):
        c_similarity.append([])
        for j in range(i+1):
            if i != j:
                deneme = np.copy(X[i])
                temp_append = np.append(deneme,X[j])
                np.array(temp_append,dtype=object)

            else :
                print(i)
                temp_append = np.copy(X[i])
            vec1 = TfidfVectorizer().fit_transform(temp_append)
            print(i)
            print(j)
            print(vec1.size)
            a = cosine_similarity(vec1)
            np.array(vec1,dtype=object)
            c_similarity[i].append(a)
            vec1
            a

    for i in range(40):
        copy_cs.append([])
        np_array.append([])
        for j in range(i+1):
            copy_cs[i].append(c_similarity[i][j])
            np_array[i] = np.asarray(c_similarity[i][j])


def max_value_csv():
    max_value = 0
    max_x = 0
    max_y = 0
    target = 1
    max_new_value = -1
    for i in range(40):
        for i in range(20):
            for j in range(i):
                max_value_ind = np_array[i+1][j+1] > target - 0.01
                np_array[i + 1][j + 1] = max_new_value
                max = np.max(np_array[i + 1][j + 1])
                if (max > max_value):
                    max_x = i + 1
                    max_y = j + 1
            target = max_value - 0.001
            x = np.where(np_array[max_x][max_y] == max_value)[0][0]
            y = np.where(np_array[max_x][max_y] == max_value)[0][1]
            with open("max_values_file.csv", 'ab') as file:
                file.write(bytes(text[x], 'UTF-8'))
                file.write(bytes('\n', 'UTF-8'))
                file.write(bytes(text[y], 'UTF-8'))
                file.write(bytes('\n', 'UTF-8'))
                file.write(bytes('\n', 'UTF-8'))
                file.close()
def thread():
    for i in range(40):
        text.append([])
    if __name__ == "__main__":
        for i in range(40):
            k = i + 1

            t = Thread(target=DB, args=(k,))
            t.start()
            sleep(5)
        t.join()
thread()
cursor.close()
cnx.close()
vec_tr()
similarity()
max_value_csv()