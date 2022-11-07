from matplotlib import pyplot as plt
import numpy as np
import pandas as pd
import math


def particlesvsT():
    df = pd.read_json('ParticlesvsTime2.json')
    plt.plot(df['time'], df['5.0'], label='5')
    plt.plot(df['time'], df['10.0'], label='10')
    plt.plot(df['time'], df['15.0'], label='15')
    plt.plot(df['time'], df['20.0'], label='20')
    plt.plot(df['time'], df['30.0'], label='30')
    plt.plot(df['time'], df['50.0'], label='50')
    plt.legend()
    plt.xlabel("Tiempo [S] ", fontsize=16)
    plt.ylabel("Partículas", fontsize=16)
    plt.show()


def ej1_secondplot(qs, errors):
    frecuencias = [5.0, 10.0, 15.0, 20.0, 30.0, 50.0]
    plt.scatter(frecuencias, qs)
    plt.errorbar(frecuencias, qs, yerr=errors, fmt="o")
    plt.xlabel("Frequency", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()


def ej2(particles, times, ds):
    qs = []
    q_errors = []
    for i in range(len(ds)):
        aux = np.polyfit(times[i], particles[i], 1)
        qs.append(aux[1] / aux[0])
        q_errors.append(i + 1)
    plt.scatter(ds, qs)
    plt.errorbar(ds, qs, yerr=q_errors, fmt="o")
    plt.xlabel("Caudal", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()

def filterNan(frequencyList):
    aux = []
    for frequency in frequencyList:
        if frequency == frequency:
            aux.append(frequency)

    return aux

def calculate_errors():
    frecuencias = ['5.0', '10.0', '15.0', '20.0', '30.0', '50.0']
    df1 = pd.read_json('Qlist0.json')
    df2 = pd.read_json('Qlist1.json')
    df3 = pd.read_json('Qlist2.json')
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

qAverage, errors = calculate_errors()
ej1_secondplot(qAverage, errors)