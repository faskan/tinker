
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FetchInteractionsServiceFout {
    private final IVRInteractionRepository ivrInteractionRepository;
    private final WebInteractionRepository webInteractionRepository;
    private final AppInteractionRepository appInteractionRepository;

    public FetchInteractionsServiceFout(IVRInteractionRepository ivrInteractionRepository,
                                        WebInteractionRepository webInteractionRepository,
                                        AppInteractionRepository appInteractionRepository) {
        this.ivrInteractionRepository = ivrInteractionRepository;
        this.webInteractionRepository = webInteractionRepository;
        this.appInteractionRepository = appInteractionRepository;
    }

    //    public List<Interaction> fetchInteractions(List<InteractionType> interactionTypes) {
//        return Stream.of(
//                        interactionTypes.contains(InteractionType.IVR) ? faultSafe(ivrInteractionRepository::fetchInteractions) : Stream.<Interaction>of(),
//                        interactionTypes.contains(InteractionType.WEB) ? faultSafe(webInteractionRepository::fetchInteractions) : Stream.<Interaction>of(),
//                        interactionTypes.contains(InteractionType.APP) ? faultSafe(appInteractionRepository::fetchInteractions) : Stream.<Interaction>of())
//                .parallel()
//                .flatMap(s -> s)
//                .toList();
//    }
    public List<Interaction> fetchInteractions(List<InteractionType> interactionTypes) {
        return List.of(
                        interactionTypes.contains(InteractionType.IVR) ? faultSafe(ivrInteractionRepository::fetchInteractions) : Stream.<Interaction>of(),
                        interactionTypes.contains(InteractionType.WEB) ? faultSafe(webInteractionRepository::fetchInteractions) : Stream.<Interaction>of(),
                        interactionTypes.contains(InteractionType.APP) ? faultSafe(appInteractionRepository::fetchInteractions) : Stream.<Interaction>of()
                )
                .stream() // Use List.stream() instead of Stream.of()
                .parallel()
                .flatMap(s -> s)
                .toList();
    }


    private Stream<Interaction> faultSafe(Supplier<Stream<Interaction>> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            return Stream.of();
        }
    }

    public static void main(String[] args) {
        FetchInteractionsServiceFout fetchInteractionsService = new FetchInteractionsServiceFout(
                new IVRInteractionRepository(),
                new WebInteractionRepository(),
                new AppInteractionRepository());
        fetchInteractionsService.fetchInteractions(List.of(InteractionType.IVR, InteractionType.WEB, InteractionType.APP));
    }

}

class Interaction {
    String id;
    String message;
    InteractionType type;
}

enum InteractionType {
    IVR,
    WEB,
    APP
}

interface InteractionRepository {
    Stream<Interaction> fetchInteractions() throws InterruptedException;
}

class IVRInteractionRepository implements InteractionRepository {
    @Override
    public Stream<Interaction> fetchInteractions() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Stream.of(new Interaction());
    }
}

class WebInteractionRepository implements InteractionRepository {
    @Override
    public Stream<Interaction> fetchInteractions() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Stream.of(new Interaction());
    }

}

class AppInteractionRepository implements InteractionRepository {
    @Override
    public Stream<Interaction> fetchInteractions() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Stream.of(new Interaction());
    }
}