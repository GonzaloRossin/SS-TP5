from matplotlib import pyplot as plt
import numpy as np
import pandas as pd
import math


def particlesvsT():
    df = pd.read_json('ParticlesvsTime0.json')
    frecuencias = ['5.0', '10.0', '15.0', '20.0', '30.0', '50.0']
    for frecuencia in frecuencias:
        list1 = list(df[frecuencia])
        list1 = filterNan(list1)
        list2 = list(df['time' + frecuencia])
        list2 = filterNan(list2)
        plt.plot(list2, list1, label='w = ' + frecuencia)
    plt.legend()
    plt.xlabel("Tiempo [S] ", fontsize=16)
    plt.ylabel("Partículas", fontsize=16)
    plt.show()


def ej1_secondplot(qs, errors):
    frecuencias = [5.0, 10.0, 15.0, 20.0, 30.0, 50.0]
    plt.scatter(frecuencias, qs)
    plt.errorbar(frecuencias, qs, yerr=errors, fmt="o")
    plt.xlabel("Frequencia", fontsize=16)
    plt.ylabel("Q [1/s]", fontsize=16)
    plt.show()


def ej2(qs, errors):
    frecuencias = [3, 4, 5, 6]
    plt.scatter(frecuencias, qs)
    plt.errorbar(frecuencias, qs, yerr=errors, fmt="o")
    plt.xlabel("D [cm]", fontsize=16)
    plt.ylabel("Q [1/s]", fontsize=16)
    plt.show()


def filterNan(frequencyList):
    aux = []
    for frequency in frequencyList:
        if frequency == frequency:
            aux.append(frequency)

    return aux


def calculate_errors(path1, path2, path3, frecuencias):
    df1 = pd.read_json(path1)
    df2 = pd.read_json(path2)
    df3 = pd.read_json(path3)
    errors = []
    qaverage = []
    for frecuencia in frecuencias:
        list1 = list(df1[frecuencia])
        list1 = filterNan(list1)
        list2 = list(df2[frecuencia])
        list2 = filterNan(list2)
        list3 = list(df3[frecuencia])
        list3 = filterNan(list3)
        q1 = np.average(list1)
        q2 = np.average(list2)
        q3 = np.average(list3)
        qs = np.array([q1, q2, q3])
        std = np.std(qs)
        errors.append(std)
        qaverage.append(np.average(qs))

    return qaverage, errors


qAverage, errors = calculate_errors('Qlist0.json', 'Qlist1.json', 'Qlist2.json', ['5.0', '10.0', '15.0', '20.0', '30.0', '50.0'])
qAverage2, errors2 = calculate_errors('QlistDoor0.json', 'QlistDoor1.json', 'QlistDoor2.json', ['3.0', '4.0', '5.0', '6.0'])
ej1_secondplot(qAverage, errors)
ej2(qAverage2, errors2)
#particlesvsT()
