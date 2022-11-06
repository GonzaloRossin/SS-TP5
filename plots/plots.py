import matplotlib.pylab as plt
import pandas as pd

def QvsT():
    df = pd.read_json('Qlist.json')
    plt.plot(df['time'], df['5.0'], label='5')
    plt.plot(df['time'], df['10.0'], label='10')
    plt.plot(df['time'], df['15.0'], label='15')
    plt.plot(df['time'], df['20.0'], label='20')
    plt.plot(df['time'], df['30.0'], label='30')
    plt.plot(df['time'], df['50.0'], label='50')
    plt.legend()
    plt.xlabel("tiempo [s]", fontsize=16)
    plt.ylabel("Q [1/s]", fontsize=16)
    plt.show()

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


particlesvsT()
QvsT()
