from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import numpy as np
from bs4 import BeautifulSoup
import re
import string
import nltk
#nltk.download('stopwords')
from nltk.corpus import stopwords
from nltk import word_tokenize
data = list()

import codecs
file = codecs.open("C:\\Users\sibel\Desktop\AnneFull.txt", "r","UTF-8")
line = file.readline()
#This part clean the data from stop words, html tags and punctuations
while (line):
    texts = line.split("\t")[2]
    example1 = BeautifulSoup(texts, 'html.parser')
    letters_only = re.sub("[^a-zA-ZğüşöçıİĞÜŞÖÇ]+ ^\d*[.,]?\d*$ +", " ", example1.getText())
    letters_only.encode('utf-8')
    a = ' '.join(word.strip(string.punctuation) for word in letters_only.split())
    stops = set(stopwords.words('turkish'))
    k = ' '.join([word for word in a.split() if word not in stopwords.words("turkish")])
    lower_sent = k.lower()
    data.append(lower_sent)
    line = file.readline()

print(len(data))
# Vectorise the data
vec = TfidfVectorizer()
X = vec.fit_transform(data) # `X` will now be a TF-IDF representation of the data, the first row of `X` corresponds to the first sentence in `data`

# Calculate the pairwise cosine similarities (depending on the amount of data that you are going to have this could take a while)
S = cosine_similarity(X)
np_array = np.array(S,dtype=object)
#print(S)
#this part find max cosine similarity pairs
def max_min_value_csv():
    target_min = 0
    target_max = 1
    max_new_value = -1
    min_new_value = 1000
    for i in range(40):
        #max_value_ind keeps location of max cosine similarity
        max_value_ind = np_array > target_max - 0.01
        #max values of np_array is 1. These values come from calculation of cosine similarity with itself so I eliminate them.
        np_array[max_value_ind] = max_new_value
        #find max cosine similarity value
        max = np.max(np_array)
        #turn the string to write file
        max_str = str(max)
        #decrease max value because find next max value
        target_max = max - 0.001
        #find location of max value
        x = np.where(np_array == max)[0][0]
        y = np.where(np_array == max)[0][1]
        #write to file text pairs and their cosine value
        with open("max_values_file.csv", 'ab') as file:
            file.write(bytes(max_str, 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.write(bytes(data[x], 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.write(bytes(data[y], 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.close()
    #here find min cosine value and their pairs
    for i in range(10):
       ## min_value_ind = np_array < target_min + 0.000000001
       ## np_array[min_value_ind] = min_new_value
        min = np.min(np_array)
        min_str = str(min)
        target_min = min + 0.00001
        x = np.where(np_array == min)[0][0]
        y = np.where(np_array == min)[0][1]
        np_array[np.where(np_array == min)] = 10
        with open("min_values_file.csv", 'ab') as file:
            file.write(bytes(min_str, 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.write(bytes(data[x], 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.write(bytes(data[y], 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.write(bytes('\n', 'UTF-8'))
            file.close()

max_min_value_csv()